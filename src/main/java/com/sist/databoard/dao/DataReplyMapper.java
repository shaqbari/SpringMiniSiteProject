package com.sist.databoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DataReplyMapper {

	@Select("Select count(*) From dataReply"
			+ " Where bno=#{bno}")
	public int replyCount(int bno);	
	
	@Select("Select no, bno, id, name, msg, TO_CHAR(regdate, 'YYYY-MM_DD HH24:MI:SS') as strDay, group_tab"
			+ " From dataReply "
			+ " Where bno=#{bno}"
			+ " ORDER BY group_id DESC, group_step ASC")
	public List<DataReplyVO> replyListData(int bno);
	
	@Insert("Insert Into dataReply Values("
			+ " dr_no_seq.nextVal, #{bno}, #{id}, #{name}, #{msg}, Sysdate, ("
			+ " Select NVL(MAX(group_id)+1, 1) From dataReply"
			+ " ), 0, 0, 0, 0)")
	public void replyNewInsert(DataReplyVO vo);
	
	@Update("Update dataReply Set"
			+ " msg=#{msg}"
			+ " Where no=#{no}")
	public void replyUpdate(DataReplyVO vo);
	
	/*------------------------����� ��� ����---------------------------------*/
	//1.�θ��� ���� ��������
	@Select("Select group_id, group_step, group_tab"
			+ " From dataReply"
			+ " Where no=#{no}")
	public DataReplyVO replyParentInfoData(int no);

	/*						gi  gs  gt ro de(������� ����)
	 * 			AAAA		1   0   0  0   2
	 * 			 ->BBBB     1   1   1  1
	 * 			  ->cccc    1   2   2  2
	 * 			 ->DDDD     1   2   2  1
	 * 
	 * 				�������߱� ���� ������� a�� ��������� b�� c�� gs�� ����
	 *  					gi  gs  gt
	 * 			AAAA		1   0   0
	 * 			 ->DDDD     1   1   1
	 * 			 ->BBBB     1   2   1
	 * 			  ->cccc    1   3   2
	 * 			 
	 * */
	//2.  ���� ����� ������ ��۵��� gs����
	@Update("Update dataReply Set"
			+ " group_step=group_step+1"
			+ " Where group_id=#{group_id}"
			+ " And group_step>#{group_step}")
	public void replyStepIncrement(DataReplyVO vo);
	//3. ����� ��� ����
	@Insert("Insert Into dataReply Values("
			+ " dr_no_seq.nextVal, #{bno}, #{id}, #{name}, #{msg}, Sysdate, #{group_id}"
			+ " , #{group_step}, #{group_tab}, #{root}, 0)")
	public void replyReInsert(DataReplyVO vo);
	//4. ���� ����� depth����
	@Update("Update dataReply Set"
			+ " depth=depth+1"
			+ " Where no=#{no}")
	public void replyDepthIncrement(int no);
	
	/******����************/
	//1.������ ��� ������������
	@Select("Select depth, root From dataReply"
			+ " Where no=#{no}")
	public DataReplyVO replyGetDepthData(int no);
	//2.1. depth�� 0�϶� �׳� ����
	@Delete("Delete From dataReply"
			+ " Where no=#{no}")
	public void replyDelete(int no);
	
	/*                  gi    gs     gt  root
	 * 1 aaaa            1     0      0    0
	 * 2  ->bbbb         1     1      1    1
	 * 3  	->cccc       1     2      2    2
	 * 		 ->gggg      1     3      3    3
	 * 4  ->dddd         1     3      1    1
	 * 5  	->eeee       1     4      2    4
	 * 6  ffff           2     0      0    0
	 * �������(���ȣ�� �̿�, ������ ������� �÷��ϳ� �� �ʿ�)�� �ƴ� �����ȰԽù��̶� ǥ��
	 * */	
	@Update("Update dataReply Set"
			+ " msg='�����ڰ� ������ ����Դϴ�.'"
			+ " Where no=#{no}")
	public void replyMsgUpdate(int no);
	
	@Update("Update dataReply Set"
			+ " depth=depth-1"
			+ " Where no=#{no}")
	public void replyDepthDecrement(int no);
	
	//�Խù� ������ ��� ��� ����
	@Delete("Delete From dataReply"
			+ " Where bno=#{bno}")
	public void replyAllDelete(int bno);
}













