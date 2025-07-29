package kr.co.sist.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

	// 아이디를 키로 MemberDTO를 저장
	private final Map<String, MemberDTO> memberMap=new HashMap<String, MemberDTO>();
	
	@PostMapping("/member")
	public MessageDTO addMember( @RequestBody MemberDTO mDTO ) {
		//@RequestBody - 서버로 들어온 JSONObject가 자바의 객체로 바뀌어서 저장이 된다. 
		String msg="추가실패 - 아이디가 존재합니다.";
		String id=mDTO.getId();
		
		if(!memberMap.containsKey(mDTO.getId())) {
			memberMap.put( id, mDTO);
			msg="추가성공";
		}//if
		System.out.println("addMember---"+memberMap);
		MessageDTO msgDTO=new MessageDTO(msg);
		System.out.println(msgDTO);
		return msgDTO;
	}//addMember
	
	
	@GetMapping("/members")
	//RestController는 return 객체 (Map, List, set, DTO )를 JSON 문자열로 변환하여 응답
	public Collection<MemberDTO> searchAllMember() {
		Collection<MemberDTO> colection=memberMap.values();
		System.out.println( colection );
		return colection;
	}
	
	@GetMapping("/members/{id}")
	//RestController는 return 객체 (Map, List, set, DTO )를 JSON 문자열로 변환하여 응답
	public MemberDTO searchOneMember(@PathVariable String id) {
		System.out.println(id);
		MemberDTO mDTO=memberMap.get(id);
		System.out.println( mDTO );
		return mDTO;
	}//searchOneMember
	
	@PutMapping("/members/{id}")
	//RestController는 return 객체 (Map, List, set, DTO )를 JSON 문자열로 변환하여 응답
	public MessageDTO modifyMember(@PathVariable String id,  @RequestBody MemberDTO mDTO) {
		MessageDTO msgDTO=new MessageDTO("아이디가 존재하지 않습니다. - 변경 실패");
		
		System.out.println( id+" / "+mDTO );
		
		if(memberMap.containsKey(id)) {
			msgDTO.setMessage(id+"회원정보를 변경하였습니다.");
			memberMap.put(id, mDTO);
		}//if
		
		return msgDTO;
	}//modifyMember
	
	@DeleteMapping("/members/{id}")
	//RestController는 return 객체 (Map, List, set, DTO )를 JSON 문자열로 변환하여 응답
	public MessageDTO removeMember(@PathVariable String id) {
		MessageDTO msgDTO=new MessageDTO("아이디가 존재하지 않습니다.",false);
		
		if(memberMap.containsKey(id)) {
			msgDTO.setMessage(id+"회원 정보를 삭제하였습니다.");
			msgDTO.setFlag(true);
			memberMap.remove(id);
		}//if
		
		return msgDTO;
	}//modifyMember
	
}//class
