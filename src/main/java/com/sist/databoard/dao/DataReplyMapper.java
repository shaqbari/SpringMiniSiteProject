package com.sist.databoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface DataReplyMapper {

	@Select("Select count(*) From dataReply"
			+ " Where bno=#{bno}")
	public int replyCount(int bno);	
	
	@Select("Select no, bno, id, name, msg, TO_CHAR(regdate, 'YYYY-MM_DD HH24:MI:SS') as strDay, group_tab"
			+ " From dataReply "
			+ " Where bno=#{bno}"
			+ " ORDER BY group_id DESC, group_step ASC")
	public List<DataReplyVO> replyListData(int bno);
	
	
	
}
