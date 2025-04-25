<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rem.admin.board.faq.vo.FAQBoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@include file="/WEB-INF/include/category.jsp" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainBoard/mainBoard.css">
<script defer src="<%=request.getContextPath() %>/js/mainBoard/mainBoardFAQList.js"></script>

<%
	String tabName = (String)request.getAttribute("tabName");
	if(tabName==null || tabName==""){
		tabName = "account";
	}
	@SuppressWarnings("unchecked")
	List<FAQBoardVO> list = (List<FAQBoardVO>)request.getAttribute("FAQList");
	List<FAQBoardVO> listAccount = new ArrayList<>();
	List<FAQBoardVO> listTransaction = new ArrayList<>();
	List<FAQBoardVO> listSafeTransaction = new ArrayList<>();
	List<FAQBoardVO> listStore = new ArrayList<>();
	List<FAQBoardVO> listEtc = new ArrayList<>();
	for(FAQBoardVO vo : list){
		if(vo.getFaq_type()==1)
			listAccount.add(vo);
		else if(vo.getFaq_type()==2)
			listTransaction.add(vo);
		else if(vo.getFaq_type()==3)
			listSafeTransaction.add(vo);
		else if(vo.getFaq_type()==4)
			listStore.add(vo);
		else if(vo.getFaq_type()==5)
			listEtc.add(vo);
	}
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String accountListJson = gson.toJson(listAccount);
    String transactionListJson = gson.toJson(listTransaction);
    String safeTransactionListJson = gson.toJson(listSafeTransaction);
    String storeListJson = gson.toJson(listStore);
    String etcListJson = gson.toJson(listEtc);
%>
<script>
const board = `<%=request.getAttribute("board")%>`;
const urlContextPath = "<%=request.getContextPath()%>";
const accountList = <%=accountListJson%>;
const transactionList = <%=transactionListJson%>;
const safeTransactionList = <%=safeTransactionListJson%>;
const storeList = <%=storeListJson%>;
const etcList = <%=etcListJson%>;
const urlView = `<%=request.getContextPath()%>/main/${board}/view.do?${board}No=`;
let tabName="<%=tabName%>";
console.log("defer script 로드 전: ", tabName);

const tabItems = document.querySelectorAll('.tabItems');

</script>

<div class="inner kcy_board">
	<div class="kcy_boardHeader">
		<br><br>
		<span class="bHeader_normal">자주 묻는 질문</span>
	</div>
	<div class="kcy_boardFAQTab">
		<div class="tabItems" id="kcy_account" data-tabName="account">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">회원/계정</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_transaction" data-tabName="transaction">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">운영정책</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_safe_transaction" data-tabName="safeTransaction">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">안전결제</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_store" data-tabName="store">
			<a class="tabA" href="javascript:void(0)">
				<span class="tabItemSpan">거래분쟁</span>
			</a>
		</div>ㅣ
		<div class="tabItems" id="kcy_etc" data-tabName="etc">
			<a class="tabA" href="javascript:void(0)">
					<span class="tabItemSpan">기타</span>
			</a>
		</div>
	</div>
	<hr style="color:#cecece;">
	
	<div class="kcy_boardList_div" >
		<ul class="kcy_boardList">
		</ul>
	</div>
	
	<hr style="color:#cecece;"><br>
	<div class="buttons">
	</div>
</div>
<!-- ■■■■■■■■■■■■■■■■■■■■ -->


<%@include file="/WEB-INF/include/footer.jsp" %>