<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/resources/taglib.jsp"%>
<html>
	<head>
		<%@ include file="/resources/resources.jsp"%>
	</head>
	<body>
		<div align="center" class="admin_title1">
			——用户列表管理——
		</div>
		<hr>
		<s:form action="userSave.action" name="form1">
			<s:hidden name="id" />
			<s:hidden name="methodName" />
			<table width="100%" border="1" cellpadding="10" cellspacing="0"
				bordercolor="#FFFFFF" bgcolor="#EEEEEE">
				<tr>
					<td width="22%" align="right" valign="middle">
						用户名称：
					</td>
					<td width="78%">
						<s:textfield name="username" size="30"/>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right" valign="middle">
						密码：
					</td>
					<td width="78%">
						<s:textfield name="password"  size="30"/>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right" valign="middle">
						姓名：
					</td>
					<td width="78%">
						<s:textfield name="realname"  size="30"/>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right" valign="middle">
						邮箱：
					</td>
					<td width="78%">
						<s:textfield name="email"  size="30"/>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right" valign="middle">
						联系电话：
					</td>
					<td width="78%">
						<s:textfield name="telphone"  size="30"/>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right" valign="middle">
						联系地址：
					</td>
					<td width="78%">
						<s:textfield name="address"  size="30"/>
					</td>					
				</tr>
				<tr>
					<td align="right" valign="middle">
						免费易拍币：	
					</td>
					<td>
						<s:textfield name="freecur"  size="30"/>
					</td>
				</tr>
				<tr>
					<td align="right" valign="middle">
						收费易拍币：	
					</td>
					<td>
						<s:textfield name="paycur"  size="30"/>
					</td>
				</tr>
				<tr align="right">
					<td colspan="2">
						<div align="center">
							<input type="submit" name="Submit" value=" 保存设置 ">
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>