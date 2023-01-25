package com.multi.drd.dashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.drd.goal.GoalDTO;
import com.multi.drd.goal.GoalService;
import com.multi.drd.gym.GymDTO;
import com.multi.drd.gym.GymService;
import com.multi.drd.json.FoodObj;
import com.multi.drd.member.MemberDTO;
import com.multi.drd.memberbio.MemberBioDTO;
import com.multi.drd.memberbio.MemberBioService;
import com.multi.drd.record.RecordDTO;
import com.multi.drd.record.RecordService;
import com.multi.drd.utils.JsonUtils;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	DashboardService service;
	RecordService recordService;
	MemberBioService memberBioService;
	GoalService goalService;
	GymService gymService;

	public DashboardController() {
		super();
	}

	@Autowired
	public DashboardController(DashboardService service, RecordService recordService, MemberBioService memberBioService,
			GoalService goalService, GymService gymService) {
		super();
		this.service = service;
		this.recordService = recordService;
		this.memberBioService = memberBioService;
		this.goalService = goalService;
		this.gymService = gymService;
	}
	//사용자 기본정보
	@RequestMapping("/read")
	public String read(Model model, HttpSession session) {
		// 세션멤버정보
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		System.out.println("member from sesseion: " + member);
		// 멤버프로필
		MemberBioDTO memberBio = memberBioService.findByPK(member.getMemberSEQ());
		System.out.println("memberBio : "+ memberBio);
		// 가장최근기록
		RecordDTO latestRecord = recordService.findLatestRecord(member.getMemberSEQ());
		System.out.println("latestRecord :"+latestRecord);
		
		//등록된 gym 정보
		GymDTO gymInfo = gymService.readGym(member.getMemberSEQ());
		System.out.println("gymInfo" + gymInfo);

			
		model.addAttribute("member", member);
		model.addAttribute("memberBio", memberBio);
		model.addAttribute("latestRecord", latestRecord);
		model.addAttribute("gymInfo", gymInfo);
		
		
		return "dashboard/dashboard";
	}
	
	//주간 목표대비 섭취칼로리, 프로틴
	@RequestMapping(value = "/read", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<Map<String, Integer>> proteinCaloryList(HttpSession session, Model model) {
		// 목표 프로틴, 목표 칼로리
		GoalDTO readGoal = goalService.readGoal(((MemberDTO) session.getAttribute("member")).getMemberSEQ());
		int goalProtein = readGoal.getGoalProtein();
		int goalCalory = readGoal.getGoalCalory();

		// 일주일 단위 섭취프로틴, 섭취칼로리
		List<RecordDTO> recordWeekly = service.findByWeek(((MemberDTO) session.getAttribute("member")).getMemberSEQ());
		System.out.println("recordWeekly" + recordWeekly);
		List<FoodObj> foodlist = null;

		for (int i = 0; i < recordWeekly.size(); i++) {
			foodlist = recordWeekly.get(i).getFoodObj();
		}
		System.out.println(foodlist);
		int takeProtein = 0;
		int totalCalory = 0;

		if (foodlist != null) {////식단 기록있다면 합산
			for (int i = 0; i < foodlist.size(); i++) {
				takeProtein += foodlist.get(i).getProtein();
				totalCalory += foodlist.get(i).getCalory();

			}
		} else {//없으면 기본값
			takeProtein = 0;
			totalCalory = 0;
		}

		int restProtein = goalProtein - takeProtein; // 적정섭취와 실제섭취 간격
		int restCalory = goalCalory - totalCalory; // 적정섭취와 실제섭취 간격

		List<Map<String, Integer>> proteinAndCalory = new ArrayList<Map<String, Integer>>();

		Map<String, Integer> protein = new HashMap<String, Integer>();
		protein.put("take", takeProtein);
		protein.put("rest", restProtein);
		protein.put("goal", goalProtein);

		Map<String, Integer> calory = new HashMap<String, Integer>();
		calory.put("take", totalCalory);
		calory.put("rest", restCalory);
		calory.put("goal", goalCalory);

		proteinAndCalory.add(protein);
		proteinAndCalory.add(calory);
		System.out.println("proteinAndCalory:" + proteinAndCalory);
		model.addAttribute("proteinAndCalory", proteinAndCalory);
		
		return proteinAndCalory;

	}

	//주단위 월간그래프
	@RequestMapping(value = "/readchart", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> weekChart(HttpSession session, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		//목표시간
		GoalDTO readGoal = goalService.readGoal(((MemberDTO) session.getAttribute("member")).getMemberSEQ());
		int goalTime = readGoal.getGoalExerciseTime() * 5;
		/* 일주일단위 기록 */
		// List<AggregationResultDTO>excerciseHourWeekly =
		// service.ExcerciseHourByWeek(member.getMemberSEQ());
		//일주일단위 무산소, 유산소 운동
		List<AggregationResultDTO> cardioMinByWeek = service
				.cardioMinByWeek(((MemberDTO) session.getAttribute("member")).getMemberSEQ());
		List<AggregationResultDTO> fitnessMinByWeek = service
				.fitnessMinByWeek(((MemberDTO) session.getAttribute("member")).getMemberSEQ());
		List<Integer> goalMinByWeek = new ArrayList<Integer>();
		List<Integer> weeks = new ArrayList<Integer>();
		List<String> dates = new ArrayList<String>();

		for (int i = 0; i < cardioMinByWeek.size(); i++) {
			goalMinByWeek.add(i, goalTime);
			// => parsnig을 위한 코드
			int parsedWeek = JsonUtils.parseTotalExerciseTimeofWeek(cardioMinByWeek.get(i).get_id()).get("week");
			weeks.add(parsedWeek);
		}
		System.out.println("weeks: " + weeks);
		//각 주의 첫 월요일 날짜
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < weeks.size(); i++) {
			cal.set(Calendar.WEEK_OF_YEAR, weeks.get(i));
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			System.out.println("week format result" + sdf.format(cal.getTime()));
			dates.add(sdf.format(cal.getTime()));
		}

		map.put("cardioMinByWeek", cardioMinByWeek);
		map.put("fitnessMinByWeek", fitnessMinByWeek);
		map.put("goalMinByWeek", goalMinByWeek);
		map.put("dates", dates);

		return map;

	}
	/*네이버지도 api 관련 기능*/
	
	//모달창안 네이버지도 api
	@RequestMapping("/readGym")
	public String naverApi(Model model) {
		return "dashboard/naverweb";
	}

	//대시보드에서 등록한 헬스장 삭제
	@RequestMapping("/deleteGym")
	public String delete(HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		gymService.deleteGym(member.getMemberSEQ());
		return "redirect:/dashboard/read";
	}

	// 공공데이터 api 데이터 넘기기
	@RequestMapping(value = "/gymInfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String gymInfo(Local local, Model model) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"https://api.odcloud.kr/api/15074334/v1/uddi:950b23d1-7daf-4712-bf5d-dd2282762367?page=1&perPage=10&serviceKey=TDR42MNN1jkMxWDM03IT1ONvig9EClx5yq8zXs73MOtMj5NkEoQik%2BaCDx8PDP5uzu%2BNX7h1rhAu9wVC9E4gGA%3D%3D"); /*
																																																									 * URL
																																																									 */
		// urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") +
		// "=TDR42MNN1jkMxWDM03IT1ONvig9EClx5yq8zXs73MOtMj5NkEoQik%2BaCDx8PDP5uzu%2BNX7h1rhAu9wVC9E4gGA%3D%3D");
		// /*Service Key*/
//	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//	        urlBuilder.append("&" + URLEncoder.encode("faciNm","UTF-8") + "=" + URLEncoder.encode("365당구클럽", "UTF-8")); /*체육시설명*/
//	        urlBuilder.append("&" + URLEncoder.encode("fcobNm","UTF-8") + "=" + URLEncoder.encode("당구장업", "UTF-8")); /*업종명*/
//	        urlBuilder.append("&" + URLEncoder.encode("ftypeNm","UTF-8") + "=" + URLEncoder.encode("당구장", "UTF-8")); /*시설유형명*/
//	        urlBuilder.append("&" + URLEncoder.encode("faciRoadAddr1","UTF-8") + "=" + URLEncoder.encode("당구장", "UTF-8")); /*시설유형명*/
//	        urlBuilder.append("&" + URLEncoder.encode("faciAddr1","UTF-8") + "=" + URLEncoder.encode("강원도 .. 리 xx", "UTF-8")); /*지번주소*/
		URL url = new URL(urlBuilder.toString());
		System.out.println("url: " + url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String gymdata = sb.toString();
		model.addAttribute("gymdata", sb.toString());
		System.out.println(gymdata);
		return gymdata;
	}

}
