<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/resources/taglib.jsp"%>

<script type="text/javascript">
	function doSubmit() {
		user.action = "viewSaveUser.action";
		user.submit();
	}
</script>
<form action="" method="post" name="user">
	<s:hidden name="id" />
	<s:hidden name="username" />
	<s:hidden name="paycur" />
	<s:hidden name="freecur" />
	<s:hidden name="password" />
	<table width="795" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" height="20" align="center" background="images/r_top.gif">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td width="10" rowspan="3" background="images/r_left.gif">
				<img src="images/r_left.gif" width="10" height="1">
			</td>
			<td width="775" height="40" align="center" bgcolor="#FFFFFF"
				class="admin_title1">
				基本信息
			</td>
			<td width="10" rowspan="3" background="images/r_right.gif">
				<img src="images/r_right.gif" width="10" height="1">
			</td>
		</tr>
		<tr>
			<td valign="top" background="images/hr.gif" colspan="3" height="1">
			</td>
		</tr>
		<tr>
			<td valign="top" bgcolor="#FFFFFF" colspan="3">
				<table width="69%" border="0" align="center" cellpadding="10"
					cellspacing="0">
					<tr>
						<td width="23%">
							&nbsp;
						</td>
						<td width="77%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							用户名：
						</td>
						<td>
							${username }
						</td>
					</tr>
					<tr>
						<td>
							姓名：
						</td>
						<td>
							<label>
								<input type="text" name="realname" value="${realname }">
							</label>
						</td>
					</tr>
					<tr>
						<td>
							邮箱：
						</td>
						<td>
							<input type="text" name="email" value="${email }">
						</td>
					</tr>
					<tr>
						<td>
							联系电话：
						</td>
						<td>
							<input type="text" name="telphone" value="${telphone }">
						</td>
					</tr>
					<tr>
						<td>
							联系地址：
						</td>
						<td>
							<input name="address" type="text" size="50" value="${address }">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" name="Submit" value="修改"
								onclick=
	doSubmit();
>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" height="20" align="center" background="images/r_dow.gif">
				&nbsp;
			</td>
		</tr>
	</table>
</form>