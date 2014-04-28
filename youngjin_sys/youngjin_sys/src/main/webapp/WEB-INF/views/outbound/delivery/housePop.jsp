<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="cp" value="<%=request.getContextPath() %>"/>
<c:set var="rp" value='<%=request.getAttribute("javax.servlet.forward.request_uri")%>'/>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <style type="text/css">
            body {
                text-align: center;
                font-family: Arial, Gulim, sans-serif;
                padding: 0;
                margin: 0;
            }
            
            input, textarea {
                border: 0px solid orange;
                margin: 0 3px;
                padding: 0;
                
                font-family: Arial, Gulim, sans-serif;
                font-size: 3.2mm;
                overflow: hidden;
                resize: none;
                
                width: 97%;
            }
            
            #paper {
                margin: 0 auto;
                width: 170mm;
                height: 257mm;
                text-align: left;
            }
            
            #paper h1 {
                text-align: right;
                font-size: 3.5mm;
                font-weight: bold;
                margin: 0;
                padding: 0;
            }
            
            #paper table {
                width: 100%;
                /*height: 252mm;*/
                border-collapse: collapse;
                margin: 0;
                padding: 0;
                table-layout: fixed;
            }
            
            #paper table td, #paper table th {
                border: 1px solid black;
                
            }
            
            #paper table th {
                text-align: left;
                font-weight: bold;
                font-size: 2.5mm;
                padding: 0 0.3mm;
                border-bottom: 0px solid black;
                height: 4mm;
            }
            
            #paper table td {
                border-top: 0px;
                margin: 0;
                padding: 0 0.3mm;
            }
            
            #paper table td div {
                width: 100%;
            }
            
            #paper div{
            	font-size: 3.2mm;
            }
            
            td.shipper {
                height: 24mm;
            }
            
            .shipper textarea {
                width: 107mm;
                height: 23mm;
            }
            
            #paper table .booking_no {
                border-right: 0;
            }
            
            td.booking_no {
                height: 5mm;
            }
            
            .booking_no input {
                width: 95%;
            }
            
            #paper table .carrier_booking_no {
                border-left: 0;
                text-align: right;
            }
            
            .carrier_booking_no input {
                text-align: right;
                width: 90%;
            }
            
            td#booking_blank {
                height: 13mm;
            }
            
            td.consignee {
                height: 25mm;
            }
            
            td.consignee textarea {
                width: 107mm;
                height: 20mm;
            }
            
            td.forwarding_agent input {
                width: 97%;
                height: 9.5mm;
                line-height: 9.5mm;
            }
            
            td.point_and_country_of_origin {
                height: 9.5mm;
            }
            
            td.notify_party textarea {
                width: 107mm;
                height: 30mm;
            }
            
            td.also_notify_routing_and_instructions textarea {
                width: 57mm;
                height: 30mm;
            }
            
            td.pre_carriage_by,
            td.place_of_receipt,
            td.vessel_voyage_no,
            td.port_of_loading,
            td.port_of_discharge,
            td.place_of_delivery {
                height: 5mm;
            }
            
            td.pre_carriage_by input,
            td.place_of_receipt input,
            td.port_of_loading input,
            td.port_of_discharge input,
            td.place_of_delivery input {
                width: 97%;
                height: 4.5mm;
                margin-left: 0 1px;
            }
            
            td.vessel_voyage_no input{
            	width: 40%;
            	height: 4.5mm;
            	margin-left: 0 1px;
            }
            
            td.contract_for_cargo_release textarea {
                width: 57mm;
                height: 22mm;
            }
            
            #paper table th.grid {
                height: 10mm;
                text-align: center;
                line-height: 2em;
                border-bottom: 1px solid black;
                word-spacing: -0.05em;
                letter-spacing: -0.025em;
            }
            
            #paper table th.no_of_pkgs_or_containers {
                text-align: left;
            }
            
            #paper table .grid textarea {
                height: 48mm;
            }
            
            #paper table th.result {
                border-bottom: 1px solid black;
                font-size: 3.2mm;
                height: 5mm;
            }
            
            #paper table th.cont_no {
                text-align: center;
            }
            
            td.freight_rates_charges,
            td.prepaid_usd,
            td.collect_usd {
                height: 1px;
            }
            
            td.freight_rates_charges textarea,
            td.prepaid_usd textarea,
            td.collect_usd textarea {
                height: 144mm;
            }
            
            td#term div {
                text-align: justify;
                height: 36mm;
                font-size: 2.2mm !important;
            }
	    
	    @media all and (-webkit-min-device-pixel-ratio: 0) {
		    td#term div div {
			margin: 0;
			padding: 0;
			width: 72.7mm;
			
			-webkit-transform: scale(0.8, 0.92);
			-webkit-transform-origin-x: 0;
			-webkit-transform-origin-y: 0;
			line-height: 1em;
			display: inline-block;
		    }
	    }
            
            #paper table th.by {
                font-weight: normal;
                border-right: 0;
            }
            
            #paper table td.by {
                border-left: 0;
                border-bottom: 0;
                height: 6mm;
            }
            
            #paper table th.bl_no {
                border-left: 0;
                border-right: 0;
                border-top: 0;
                border-bottom: 1px solid black;
            }
            
            #paper table td.bl_no {
                height: 4.5mm;
                border-left: 0;
            }
            
            td.bl_no div{
            	font-size: 2.5mm;
            }
            
        </style>

		<script>
			var contextPath = '<c:out value="${cp}"/>';
			var realPath = '<c:out value="${rp}"/>';
			var addError = false;
			if (typeof youngjin == 'undefined') {
				youngjin = {};
			}
		</script>
		
		<%@ include file="../../../layout/include_script.jspf" %>
		
        <script type="text/javascript">    	
        </script>
    </head>
    <body onload="window.print();">
    	<c:forEach var="gbl" items="${gblList }">
	    	<div style="width: 900px; height: 1300px; background-color: white;" class="house_pdf">
		        <div id="paper">
		            <h1>BILL OF LADING</h1>
		            <table>
		                <colgroup>
		                    <col style="width: 18.82%">
		                    <col style="width: 15.34%">
		                    <col style="width: 18.12%">
		                    <col style="width: 12.50%">
		                    <col style="width: 9.66%">
		                    <col style="width: 6.39%">
		                    <col style="width: 6.68%">
		                    <col style="width: 12.50%">
		                    <col style="width: 0.0001%"> <!-- ALL IE CSS HACK -->
		                </colgroup>
		                
		                <tr>
		                    <th colspan="4" class="shipper">SHIPPER / EXPORTER</th>
		                    <th colspan="2" class="booking_no">BOOKING NO</th>
		                    <th colspan="3" class="carrier_booking_no">CARRIER BOOKING NO.</th>
		                </tr>
		                <tr>
		                    <td colspan="4" rowspan="2" class="shipper">
		                        <div>${gbl.customerName }<br/>${companyMap[gbl.consoleCompany].companyFullName }<br/> ${companyMap[gbl.consoleCompany].address }</div>
		                    </td>
		                    <td colspan="2" class="booking_no">
		                        <div>${(fn:contains(gbl.no, "-house")) ? fn:substring(gbl.no, 0, fn:length(gbl.no) - 7) : gbl.no }</div>
		                    </td>
		                    <td colspan="3" class="carrier_booking_no">
		                        <div>${house.carrierBookingNo }</div>
		                    </td>
		                </tr>
		                <tr>
		                    <td id="booking_blank" colspan="5"></td>
		                </tr>
		                
		                <tr>
		                    <th colspan="4" class="consignee">CONSIGNEE</th>
		                    <th colspan="5" class="forwarding_agent">FORWARDING AGENT</th>
		                </tr>
		                <tr>
		                    <td colspan="4" rowspan="3" class="consignee">
		                        <div>
		                        	${gbl.customerName }<br/>
		                        	${gbl.houseConsignee }	                        	
		                        </div>
		                    </td>
		                    <td colspan="5" class="forwarding_agent">
		                        <div>${companyMap[gbl.houseCompany].companyFullName }</div>
		                    </td>
		                </tr>
		                <tr>
		                    <th colspan="5" class="point_and_country_of_origin">POINT AND COUNTRY OF ORIGIN</th>
		                </tr>
		                <tr>
		                    <td colspan="5" class="point_and_country_of_origin">KOREA</td>
		                </tr>
		                
		                <tr>
		                    <th colspan="4" class="notify_party">NOTIFY PARTY</th>
		                    <th colspan="5" class="also_notify_routing_and_instructions">ALSO NOTIFY-ROUTING AND INSTRUCTIONS</th>
		                </tr>
		                <tr>
		                    <td colspan="4" class="notify_party">
		                        <textarea name="notify_party"></textarea>
		                    </td>
		                    <td colspan="5" class="also_notify_routing_and_instructions">
		                        <textarea name="also_notify_routing_and_instructions"></textarea>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th colspan="2" class="pre_carriage_by">PRE-CARRIAGE BY</th>
		                    <th colspan="2" class="place_of_receipt">PLACE OF RECEIPT</th>
		                    <th colspan="5" class="contract_for_cargo_release">CONTRACT FOR CARGO RELEASE</th>
		                </tr>
		                <tr>
		                    <td colspan="2" class="pre_carriage_by">
		                        <div><input type="text" name="pre_carriage_by"></div>
		                    </td>
		                    <td colspan="2" class="place_of_receipt">
		                        <div><input type="text" name="place_of_receipt"></div>
		                    </td>
		                    <td colspan="5" rowspan="5" class="contract_for_cargo_release">
		                        <textarea name="contract_for_cargo_release"></textarea>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th colspan="2" class="vessel_voyage_no">VESSEL . VOYAGE NO.</th>
		                    <th colspan="2" class="port_of_loading">PORT OF LOADING</th>
		                </tr>
		                <tr>
		                    <td colspan="2" class="vessel_voyage_no">
		                        <div>${gbl.houseVessel }&nbsp;&nbsp;&nbsp;&nbsp;${gbl.houseVoyage }</div>
		                    </td>
		                    <td colspan="2" class="port_of_loading">
		                        <div>BUSAN, KOREA</div>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th colspan="2" class="port_of_discharge">PORT OF DISCHARGE</th>
		                    <th colspan="2" class="place_of_delivery">PLACE OF DELIVERY</th>
		                </tr>
		                <tr>
		                    <td colspan="2" class="port_of_discharge">
		                        <div>${podMap[gbl.pod].podAgentName } ${podMap[gbl.pod].podFull }</div>
		                    </td>
		                    <td colspan="2" class="place_of_delivery">
		                        <div><input type="text" name="place_of_delivery"></div>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th class="marks_and_numbers grid">MARKS AND NUMBERS</th>
		                    <th class="no_of_pkgs_or_containers grid">NO. OF PKGS<br>OR CONTRAINERS</th>
		                    <th colspan="3" class="description_of_packages_and_goods grid">DESCRIPTION OF PACKAGES AND GOODS</th>
		                    <th colspan="2" class="gross_weight grid">GROSS WEIGHT</th>
		                    <th colspan="2" class="measurement grid">MEASUREMENT</th>
		                </tr>
		                <tr>
		                    <td class="marks_and_numbers grid">
		                        <div>${fn:substring(gbl.no, 0, fn:length(gbl.no) - 7) }<br/>${gbl.customerName }</div>
		                    </td>
		                    <td class="no_of_pkgs_or_containers grid">
		                       	<div>${gbl.pcs }L/VAN(s)</div>
		                    </td>
		                    <td colspan="3" class="description_of_packages_and_goods grid">
		                        <textarea name="description_of_packages_and_goods"></textarea>
		                    </td>
		                    <td colspan="2" class="gross_weight grid">
		                        <div><fmt:formatNumber value="${gbl.lbs / 2.2046 }" pattern=".00" />KGS</div>
		                    </td>
		                    <td colspan="2" class="measurement grid">
		                        <div><fmt:formatNumber value="${gbl.cuft / 35.315 }" pattern=".00" />CBM</div>
		                    </td>
		                </tr>
		                <tr>
		                    <th class="cont_no result">CON'T NO:</th>
		                    <td colspan="2" class="cont_no result">
		                        <div>${house.contNo }</div>
		                    </td>
		                    <th class="seal_no result">SEAL NO:</th>
		                    <td colspan="5" class="sesal_no result">
		                        <div>${house.sealNo }</div>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <th colspan="2" class="freight_rates_charges grid">FREIGHT RATES CHARGES, WEIGHTS AND/OR<br>MEASUREMENTS(SUBJECT TO CORRECTION)</th>
		                    <th class="prepaid_usd grid">PREPAID U.S.&#36;</th>
		                    <th class="collect_usd grid">COLLECT U.S.&#36;</th>
		                    <td colspan="4" id="term" rowspan="2">
		                        <div>
					    <div> <!-- Chrome CSS hack -->
						The undersigned Carrier hereby acknowledges receipt of the sealed
						container or packages or other shipping units said to contain the Goods
						described above in apparent external good order and condition unless
						otherwise stated. The shipper agrees . And the Consignee and every
						person purchasing this instrument for value, if negotiable, or otherwise
						having an interest in the Goods is advised that the receipt. custody,
						carriage and delivery of the Goods are subject to all the terms and
						conditions set forth and by incorporated by reference on this side and the
						reverse hereof, whether written, stamped or printed.
						A set of 0 originals of this bill of lading is hereby issued by the Carrier.
						Upon surrender to the Carrier of any one negotiable bill of lading, properly
						endorsed, all others shall stand void.
					    </div>
		                        </div>
		                    </td>
		                    <td></td>
		                </tr>
		                <tr>
		                    <td colspan="2" rowspan="5" class="freight_rates_charges grid">
		                        <div>FREIGHT COLLECT & ARRANGED.</div>
		                    </td>
		                    <td rowspan="5" class="prepaid_usd grid">
		                        <textarea name="prepaid_usd"></textarea>
		                    </td>
		                    <td rowspan="5" class="collect_usd grid">
		                        <textarea name="collect_usd"></textarea>
		                    </td>
		                    <td style="height:100px; "></td>
		                </tr>
		                <tr>
		                    <th colspan="5" class="place_and_date_of_bl_issue">PLACE AND DATE OF B/L ISSUE</th>
		                </tr>
		                <tr>
		                    <td colspan="5" class="place_and_date_of_bl_issue">
		                        <div style="margin: 0.5em 0 0.1em 3.5em; font-size: 3.5mm;">SEOUL, KOREA</div>
		                    </td>
		                </tr>
		                <tr>
		                    <th class="by">BY</th>
		                    <td colspan="4" class="by">
		                        <div><input type="text" name="by" style="position: relative; left: -10mm; z-index: 1; width"></div>
		                    </td>
		                </tr>
		                <tr>
		                    <td colspan="2" style="border-right: 0;"></td>
		                    <th class="bl_no">BL NO.</th>
		                    <td colspan="2" class="bl_no">
		                        <div>${fn:substring(gbl.no, 0, fn:length(gbl.no) - 7) }</div>
		                    </td>
		                </tr>
		            </table>
		        </div>
			</div>
		</c:forEach>
    </body>
</html>