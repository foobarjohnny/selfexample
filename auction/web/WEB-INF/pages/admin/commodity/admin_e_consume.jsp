<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/resources/taglib.jsp"%>
<html>
	<head>
		<%@ include file="/resources/resources.jsp"%>
		<script type="text/javascript">
	function previous() {
		var currentPage = document.getElementById("pageBean.currentPage").value;
		if (parseInt(currentPage) != 1) {
			document.getElementById("pageBean.currentPage").value = parseInt(currentPage) - 1;
			form1.submit();
		}
	}
	function next() {
		var currentPage = document.getElementById("pageBean.currentPage").value;
		var totalPage = document.getElementById("pageBean.totalPage").value;
		if (parseInt(currentPage) != (totalPage)) {
			document.getElementById("pageBean.currentPage").value = parseInt(currentPage) + 1;
			form1.submit();
		}
	}
	function insert() {
		form1.action = "consumeForward.action";
		form1.submit();
	}
</script>
	</head>
	<body>
		<div align="center" class="admin_title1">
			——用户E拍币消费明细查询——
		</div>
		<hr>
		<s:form action="consumeSearch.action" name="form1">
			<s:hidden name="pageBean.currentPage" />
			<s:hidden name="pageBean.totalPage" />
			<table width="100%" border="1" cellpadding="10" cellspacing="0"
				bordercolor="#FFFFFF" bgcolor="#EEEEEE">
				<tr>
					<td width="31%" align="center" valign="middle">
						<strong>描述</strong>
					</td>
					<td width="14%" align="center">
						<strong>用户</strong>
					</td>
					<td width="14%" align="center">
						<strong>E拍币数</strong>
					</td>
					<td width="10%" align="center">
						<strong>操作</strong>
					</td>
				</tr>
				<s:iterator value="dataList" id="data" status="status">
					<tr>
						<td align="center" valign="middle">
							<table width="100%" border="0" cellpadding="10" cellspacing="0">
								<tr>
									<td><img src="showImage.action?id=${data.comId }" height="80" width="80"></td>
									<td>${data.comname }</td>
								</tr>
							</table>
						</td>
						<td align="center" valign="middle">
							${data.username }
						</td>
						<td align="center" valign="middle">
							${data.amount }
						</td>
						<td align="center">
							<a href="consumeDelete.action?id=<s:property value="id"/>&nettype=5">删除</a>
						</td>
					</tr>
				</s:iterator>
				<!--  
				<tr align="right">
					<td colspan="7">
						共
						<s:property value="pageBean.totalRec" />
						条纪录，当前第
						<s:property value="pageBean.currentPage" />
						/
						<s:property value="pageBean.totalPage" />
						页，每页
						<s:property value="pageBean.pageRec" />
						条纪录 
						<a href="#" onclick="previous();">上一页</a>&nbsp;&nbsp;
						<a href="#" onclick="next();">下一页</a>
					</td>
				</tr>
				-->
			</table>
		</s:form>
	</body>
</html>