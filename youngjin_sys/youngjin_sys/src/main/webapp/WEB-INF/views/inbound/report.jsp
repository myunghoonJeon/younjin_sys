<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../layout/head.jspf"%>
<%-- Page 처리 Script --%>

<script type="text/javascript">
	
</script>	
	<div class="gbl_filter">	
		<ul class="freight_filter_wrap">
			<li>
				DATE : <input class="start" id=""start"" name=""start"" value=""/> ~ <input class="end" id="end" name="end" value=""/>
				<span class="analysis yj_button" >ANALYSIS</span>
				<span class="print yj_button" >PRINT </span>
			</li>
		</ul>	
	</div>
	<div>
		<table class="yj_table">
			<thead>
				<tr>
					<th>STATUS</th>
					<th colspan="2">SHIPMENT RECEIVED</th>
					<th colspan="2">MISSED RDD</th>
					<th>SHPMTS INTOSIT</th>
					<th colspan="4">SHIPMENTS DELIVERED</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>CODE-3</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>CODE-4</td>
					<td>${m4[size] }</td><td>${m4[weight] }</td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>CODE-5</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>CODE-6</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>CODE-T</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>DPM HHG MAC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>DPM HHG MSC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
								<tr>
					<td>HHG TOTAL</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>CODE-J</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>CODE-7</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>CODE-8</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>DPM UB MAC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>DPM UB MSC</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>DPM UB COM</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
												<tr>
					<td>UB TOTAL</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td>GRAND TOTAL</td>
					<td></td><td></td>
					<td></td><td></td>
					<td></td>
					<td></td><td></td><td></td><td></td>
				</tr>
			</tbody>
		</table>
	</div>	
<%@ include file="../../layout/foot.jspf"%>