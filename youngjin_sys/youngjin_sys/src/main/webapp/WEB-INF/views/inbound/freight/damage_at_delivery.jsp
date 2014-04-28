<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 
	${gbl} : gbl 정보를 가져옴
	${carrier} : carrier 정보를 가져옴
	내부 정보는
	
	GBL 클래스와 Carrier클래스에 가면 볼수 있습니다.
 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>BILL OF LADING</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <style type="text/css">
            /* VERSION 20140326-1 */


            
            body {
                margin: 0;
                padding: 0;
                text-align: center;
                
                font-family: Arial, sans-serif;
            }
            
            div, span {
                margin: 0;
                padding: 0;
            }
            
            ul {
                margin: 0;
                padding-left: 3mm;
            }
            
            /* Header */
            #header, #footer {
                text-align: left;
                margin: 0 auto;
                width: 162mm;
            }
            
            #header {
                font-size: 9pt;
                margin-bottom: 15mm;
            }
            
            .left {
                float: left;
            }
            
            .right {
                float: right;
            }
            
            /* Paper */
            #paper {
                text-align: left;
                margin: 0 auto;
                
                width: 162mm;
            }
            
            #paper table {
                border-collapse: collapse;
                width: 100%;
            }
            
            
            #paper table td {
                border: 1px solid black;
                vertical-align: top;
                
                font-size: 2.6mm;
                word-spacing: -0.15mm;
                letter-spacing: -0.09mm;
                
                margin: 0;
                padding: 0 1.5mm 0 1.5mm;
            }
            
            #paper table td input {
                height: 4.5mm;
                border: 0;
                border-bottom: 1px solid black;
                
                /* font-family: Arial, sans-serif; */
                font-family: Times New Roman, serif;
                font-size: 10pt;
                
                margin: 2px 0 0 0;
                padding: 0;
                
                width: 100%;
            }
            
            /* Line 1 - Title*/
            #paper table td.title
            {
                text-align: center;
                font-weight: bold;
                letter-spacing: -0.1mm;
                
                height: 12.5mm;
            }
            
            #paper table td.title div.small
            {
                padding-top: 1mm;
                
                font-size: 3mm;
                text-transform: uppercase;
            }
            
            #paper table td.title div.small span.big
            {
                font-size: 3.1mm;
            }
            
            #paper table td.title div.big
            {
                font-size: 3.8mm;
            }
            
            /* Line 2 - Input Box */
            #paper table td.input-1
            {
                height: 22mm;
                text-transform: uppercase;
                word-spacing: -0.11mm;
                letter-spacing: -0.1mm;
                white-space: nowrap;
            }
            
            .left_stack div {
                float: left;
            }
                        
            .new_stack, .no_stack, .left_stack div * {
                clear: both;
            }
            
            #paper table td.input-1 div.name_of_owner
            {
                width: 38.5mm;
            }
            
            #paper table td.input-1 div.rank_grade
            {
                margin-left: 12mm;
                width: 14.5mm;
            }
            
            #paper table td.input-1 div.rank_grade input
            {
                text-align: center;
            }
            
            #paper table td.input-1 div.branch_of_service
            {
                margin-left: 19.5mm;
                width: 23mm;
            }
            
            #paper table td.input-1 div.weight
            {
                margin-left: 18.5mm;
                width: 11mm;
            }
            
            #paper table td.input-1 div.weight input {
                text-align: center;
            }

            #paper table td.input-1 div.bl_no
            {
                width: 22mm;
            }
            
            #paper table td.input-1 div.bl_no input
            {
                font-size: 9pt;
            }
            
            #paper table td.input-1 div.tsp_reference_no
            {
                margin-left: 9.5mm;
                width: 24.5mm;
            }
            
            #paper table td.input-1 div.scac
            {
                margin-left: 3mm;
                width: 9mm;
            }
            
            #paper table td.input-1 div.pick_up_date
            {
                margin-left: 5mm;
                width: 15.5mm;
            }
            
            #paper table td.input-1 div.partial_delivery__title
            {
                margin-left: 4mm;
                width: 43.5mm;
                
                margin-top: 2.5mm;
                text-transform: none;
                
                text-align: right;
                letter-spacing: -0.21mm;
                word-spacing: -0.13mm;
            }
            
            #paper table td.input-1 div.partial_delivery
            {
                margin-left: 2mm;
                margin-top: 1.4mm;
                width: 10.5mm;
            }
            
            #paper table td.input-1 div.partial_delivery input
            {
                text-align: center;
                height: 3.8mm;
            }
            
            /* Line 3 - Purpose and general instructions */
            #paper table td.purpose_and_general_instructions {
                padding: 1.8mm 1.5mm 0 1.5mm;
                height: 25mm;
                font-size: 2.3mm;
            }
            
            /* Line 7-1 - Received for delivery at */
            #paper table td.received_for_delivery_at .street_address {
                width: 77mm;
            }
            
            #paper table td.received_for_delivery_at .city {
                width: 43mm;
            }
            
            #paper table td.received_for_delivery_at .state {
                width: 22mm;
            }
            
            #paper table td.received_for_delivery_at .zip {
                width: 12mm;
            }
            
            #paper table td.received_for_delivery_at .telephone_number {
                width: 33mm;
            }
            
            #paper table td.received_for_delivery_at .customer_email {
                width: 45mm;
            }
            
            #paper table td.received_for_delivery_at .signature_of_customer {
                width:4.8cm;
            }
            
            #paper table td.received_for_delivery_at div.delivery_date {
                margin-left:0.6cm;
                width:1.6cm;
            }
            
            /* Line 7-2 - Name/Address of Transportation Service Provider(TSP) */
            #paper table td.transportation_service_provider .toll_free_telephone_number {
                width: 35mm;
            }
            
            #paper table td.transportation_service_provider div.fax_number {
                margin-left: 1.7cm;
            }
            
            #paper table td.transportation_service_provider .delivering_tsp_signature {
                width: 41mm;
            }
            
            #paper table td.transportation_service_provider div.date {
                margin-left:1cm;
            }
            
            #paper table td.transportation_service_provider .date {
                width: 24mm;
            }
            textarea{resize:none; border: none; overflow-y: hidden;}
            
            /* Footer */
            #footer {
                text-align: center;
                font-weight: bold;
                font-family: Times New Roman, serif;
                
                margin-top: 4mm;
            }
        </style>
    </head>
    <body>
        <div id="header" style="font-size:7pt;">
            <strong>
            <div class="left">
                <div>Defense Transportation Regulation -- Part IV</div>
                <div style="padding-bottom: 1cm;">Personal Property</div>
            </div>
            <div class="right">
                <div>20 November 2012</div>
            </div>
            </strong>
        </div>
        
        <div id="paper">
            <table>
                <tbody>
                    <tr>
                        <td class="title" colspan="3">
                            <div class="small"><span class="big">D</span>efense <span class="big">P</span>ersonal <span class="big">P</span>roperty <span class="big">P</span>rogram <span class="big">(DP3)</span></div>
                            <div class="big">NOTIFICATION OF LOSS OR DAMAGE <span style="text-decoration: underline;">AT</span> DELIVERY</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="input-1" colspan="3">
                            <div>
                                Completed by TSP:
                            </div>
                            <div class="left_stack">
                                <div class="name_of_owner">
                                    <div>
                                        <input id="name_of_owner" type="text" name="name_of_owner" value="${gbl.shipperName }">
                                    </div>
                                    <div>
                                        Name of owner
                                    </div>
                                </div>
                                
                                <div class="rank_grade">
                                    <div>
                                        <input id="rank_grade" type="text" name="rank_grade" value="${gbl.rank }">
                                    </div>
                                    <div>
                                        Rank/Grade
                                    </div>
                                </div>
                                
                                <div class="branch_of_service">
                                    <div>
                                        <input id="branch_of_service" type="text" name="branch_of_service"  >
                                    </div>
                                    <div>
                                        Branch of service
                                    </div>
                                </div>
                                <div class="weight">
                                    <div>
                                    	<c:choose>
                                    		<c:when test="${gbl.code eq '4' or gbl.code eq'3' or gbl.code eq '5' or gbl.code eq 'T' or gbl.code eq 't' }">
                                    			<c:set var ="weight" value="${gbl.netWeight }"/>
                                    		</c:when>
                                    		<c:when test="${gbl.code eq'7' or gbl.code eq '8' or gbl.code eq 'J' or gbl.code eq 'j' }">
                                    			<c:set var ="weight" value="${gbl.grossWeight }"/>
                                    		</c:when>
                                    	</c:choose>
                                   		<input id="weight" type="text" name="weight" value="${weight }">
                                    </div>
                                    <div>
                                        Weight
                                    </div>
                                </div>
                            </div>
                           
                            <div class="new_stack left_stack">
                                <div class="bl_no">
                                    <div>
                                        <input id="bl_no" type="text" name="bl_no" value="${gbl.gblNo }">
                                    </div>
                                    <div>
                                        BL NO.
                                    </div>
                                </div>
                                
                                <div class="tsp_reference_no">
                                    <div>
                                        <input id="tsp_reference_no" type="text" name="tsp_reference_no">
                                    </div>
                                    <div>
                                        TSP REFERENCE NO.
                                    </div>
                                </div>
                                
                                <div class="scac">
                                    <div>
                                        <input id="scac" type="text" name="scac" value="${gbl.tsp }" style="width:45px; text-align: center;">
                                    </div>
                                    <div>
                                        　							SCAC
                                    </div>
                                </div>
                                
                                <div class="pick_up_date">
                                    <div>
                                        <input id="pick_up_date" type="text" name="pick_up_date"  value="${gbl.pud }">
                                    </div>
                                    <div>
                                        Pick up date
                                    </div>
                                </div>
                                
                                <div class="partial_delivery__title">
                                    IS THIS A PARTIAL DELIVERY (Y or N)?
                                </div>
                                
                                <div class="partial_delivery">
                                    <input id="partial_delivery" type="text" name="partial_delivery">
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" class="purpose_and_general_instructions">
                            <strong>PURPOSE AND GENERAL INSTRUCTIONS:</strong>
                            <ul>
                                <li>To provide the Transportation Service Provider (TSP) notice of loss or damage discovered AT the time of delivery.</li>
                                <li>The customer (or their designeated representative) and the TSP's delivery representative must jointly complete this document.</li>
                                <li>List in <strong>NOTED LOSS AND/OR DAMAGE</strong> section below all damage and missing items noticed before TSP's representative departs.</li>
                                <li><strong>Do NOT leave blank. If no loss or damage is discovered at the time of delivery, write "NONE" in DESCRIPTION OF DAMAGE.</strong></li>
                                <li><strong>THIS DOES NOT CONSTITUTE "FILING A CLAIM". CLAIM MUST BE FILED VIA DPS CLAIMS MODULE - http://www.move.mil/.</strong></li>
                            </ul>
                            	<center><font style="font-weight: bold; font-size:8pt;">NOTED LOSS AND/OR DAMAGE</font></center>
                            	if more than one page is needed. include your name. bill of Landing No. and number the Page____of Page____on each page used.
                        </td>
                    </tr>
                    <tr>
                        <td style="width:1.2cm; height:0.7cm; font-size: 7pt;text-align:center; font-weight: bold;">INVENTORY<BR>NO.</td><td style="width:6cm; font-size:7pt;text-align:center; font-weight: bold;">ITEM</td>
                        <td style="font-size:7pt;text-align:center; font-weight: bold;">DESCRIPTION OF DAMAGE<font style="font-size:5pt;">(iftestsetsfewfwefwef)<br/>(Electronic items, provide brand & model number)</font></td>
                    </tr>
                    <c:forEach var="roof" begin="0" end="7" step="1">
                    <tr>
	                   <td style="width:1.2cm; height:0.5cm; font-size: 7pt;text-align:center; font-weight: bold;">　</td>
	                   <td style="width:6cm; font-size:7pt;text-align:center; font-weight: bold;">　</td>
	                   <td style="font-size:7pt;text-align:center; font-weight: bold;">　</td>
	                 </tr>
                  	</c:forEach>
                  	<tr>
                  	<td colspan="3" style="font-weight: bold; font-size:6.5pt;">NOTE : TSP is responsible for one-time placement of items during delivery. if requested, the TSP will unpack and remove cartons<br/>
                  	to the customer's satisfaction. Member requested unpacking and removal of cartons? YES____ NO____</td>
                  	</tr>
                  	<tr>
                  		<td colspan="3" style="padding-bottom:2mm;padding-top:2mm; padding-left: 2.5mm; font-weight:bold; font-size:6.5pt ;border-top-style: none; border-bottom-style: none; ">PLEASE READ CAREFULLY BEFORE SIGNING -- THIS IS CUSTOMER'S NOTIFICATION OF LOSS AND/OR DAMAGE <font style="text-decoration: underline;">AT</font> DELIVERY</td>
                  	</tr>
                  	<tr>
                  	<td style="padding-bottom:2mm;border-top-style: none; border-bottom-style: none;"colspan="3">
                  		<font style="font-weight:bold; font-size:7pt;">By signing below, customer acknowledges receip of:</font><br>
                  		<ul>
                  			<li>
                  			<font style=" font-size:6pt;">
                  			One(1) copy of this NOTIFICATION OF LOSS OR DAMAGE <strong style="text-decoration: underline;">AT</strong> DELIVERY and one (1)copy of the NOTIFICATION OF LOSS OR DAMAGE <strong style="text-decoration: underline;">AFTER</strong> DELIVERY.
                  			</font>
                  			</li>
                  		</ul>
                    </td>
                    </tr>
                    <tr>
                    <td style="border-top-style: none;" colspan="3">
                    	<font style="font-weight:bold; font-size:7pt;">Customer understands that he/she:</font><br>
                  		<ul>
                  			<font style=" font-size:6.6pt;">
                  			<li><strong>Will receive from the delivering TSP a "NOTIFICATION OF LOSS OR DAMAGE <span style="text-decoration: underline;">AFTER</span> DELIVERY" document to identify loss or
                  			 damage found after delivery. This notification document will provide instructions on how to file a claim on-line.</strong></li>
                  			<li>Can provide notification to the TSP within 75 days by entering the information from the AFTER Delivery document into the DPS on-line claims module or mail NOTIFICATION OF LOSS OR DAMAGE <strong style="text-decoration: underline;">AFTER</strong> DELIVERY document to the TSP by certified return receipt. fax or electronic dispatch.</li>
                  			<li>Will NOT be eligible for loss or damage recovery by the TSP or Govermment for any item not identified within 75 day period after delivery</li>
                  		</ul>
                  		</font>
                  		</td>
                  	</tr>
                  	<tr>
                  		<td colspan="2" class="received_for_delivery_at">
                            <div>
                                Received for Delivery at:
                            </div>                    

                            <div class="new_stack left_stack">
                                <div class="street_address">
                                    <div>
                                        <input class="street_address" id="street_address" type="text" name="street_address" value="${gbl.address }">
                                    </div>
                                    <div>
                                        Street Address
                                    </div>
                                </div>
                            </div>

                            <div class="new_stack left_stack">
                                <div class="city">
                                    <div>
                                        <input class="city" id="city" type="text" name="city">
                                    </div>
                                    <div>
                                        City
                                    </div>
                                </div>

                                <div class="state">
                                    <div>
                                        <input id="state" type="text" name="state">
                                    </div>
                                    <div>
                                        State
                                    </div>
                                </div>
                                
                                <div class="zip">
                                    <div>
                                        <input id="zip" type="text" name="zip">
                                    </div>
                                    <div>
                                        Zip
                                    </div>
                                </div>
                            </div>
                            
                            <div class="new_stack left_stack">
                                <div class="telephone_number">
                                    <div>
                                        <input class="telephone_number" id="telephone_number" type="text" name="telephone_number" value="${gbl.phone }">
                                    </div>
                                    <div>
                                        Telephone Number
                                    </div>
                                </div>
                            </div>
                            
                            <div class="new_stack left_stack">
                                <div class="customer_email">
                                    <div>
                                        <input class="customer_email" id="customer_email" type="text" name="customer_email" value="${gbl.eMailAddress }">
                                    </div>
                                    <div>
                                        Customer &nbsp;Email
                                    </div>
                                </div>
                            </div>
                            
                            <div class="new_stack left_stack">
                                <div class="signature_of_customer">
                                    <div>
                                        <input class="signature_of_customer" id="signature_of_customer" type="text" name="signature_of_customer">
                                    </div>
                                    <div>
                                        Signature of Customer
                                    </div>
                                </div>
                                <div class="delivery_date">
                                    <div>
                                        <input class="delivery_date" id="delivery_date" type="text" name="delivery_date">
                                    </div>
                                    <div>
                                        &nbsp;Delivery Date
                                    </div>
                                </div>
                            </div>

                            <div class="no_stack">
                                <div>
                                    (or his/her designated representative)
                                </div>
                            </div>
                  		</td>
                        
                  		<td class="transportation_service_provider">
                  			<div class="left_stack" >
                  				Name/Address of Transportation Service Provider(TSP)
                  			</div>
                  			<div class="left_stack">
                  				
                  				<textarea rows="3" cols="45" style="border: none; font-size: 9pt;" id="nameaddressoftransportation" name="nameaddressoftransportation" >${carrier.scacFullName },&#13;&#10;${carrier.address };</textarea>
                  			</div>
                            
                  			<div>
                  				TSP Email: <input style="width:5.4cm;" id="tsp_email" name="tsp_email" value="${carrier.trafficEmail }">
                  			</div>
                            
                  			<div class="new_stack left_stack" style="margin-top: 1mm;">
                                <div class="toll_free_telephone_number">
                                    <div>
                                        <input class="toll_free_telephone_number" id="free_telephone_number" name="free_telephone_number" value="${carrier.telNo }">
                                    </div>
                                    <div>
                                        Toll-Free Telephone Number
                                    </div>
                                </div>
                                <div class="fax_number">
                                    <div>
                                        <input class="fax_number" style="width:2.3cm;" id="fax_number" name="fax_number" value="${carrier.faxNo }">
                                    </div>
                                    <div>
                                        &nbsp;&nbsp;Fax Number
                                    </div>
                                </div>
                  			</div>
                            
                  			<div class="new_stack left_stack" style="margin-top: 15mm;">
                                <div class="delivering_tsp_signature">
                                    <div>
                                        <input class="delivering_tsp_signature" id="delivering_tsp_sinature" name="delivering_tsp_sinature">
                                    </div>
                                    <div>
                                        Delivering TSP Signature
                                    </div>
                                </div>
                                
                                <div class="date">
                                    <div>
                                        <input class="date" id="date" name="date">
                                    </div>
                                    <div>
                                        &nbsp;&nbsp;Date
                                    </div>
                                </div>
                  			</div>
                  		</td>
                  	</tr>
                </tbody>
            </table>
        </div>
        
        <div id="footer">
            Figure B-2. Notification of Loss or Damage AT Delivery
        </div>
    </body>
</html>