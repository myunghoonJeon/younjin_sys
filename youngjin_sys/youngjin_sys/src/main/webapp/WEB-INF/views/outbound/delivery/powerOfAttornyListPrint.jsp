<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>POWER OF ATTORNEY</title>
        <style type="text/css">
            body
            {
                font-family: Arial, sans-serif;
                font-size: 0.9em;
                text-align: center;
            }
            
            h1
            {
                text-align: center;
                font-weight: normal;
                font-size: 1.5em;
                margin-bottom: 2em;
            }
            
            #mail
            {
                width: 14cm;
                margin: 0 auto;
                text-align: left;
            }
            
            .to_subject
            {
                margin-bottom: 1.5em;
            }
            
            .to_subject *
            {
                display:inline-block;
                vertical-align: top;
                border: 0px;
            }
            
            textarea
            {
                width: 80%;
                overflow: auto;
                height: 3.5em;
                border: 0;
            }
            
            .to_subject input
            {
                width: 80%;
            }
            
            .to_subject label
            {
                margin-right: 0.05em;
            }
            
            .spec
            {
                margin-left: 3em;
                margin-bottom: 2em;
            }
            
            .underline_box
            {
                border-width: 0px;
                border-bottom: 1px solid black;
                padding: 0.25em;
            }
            
            .weight_title
            {
                width: 7.5em;
                display: inline-block;
                overflow: visible;
                
                height: 0.8em;
                line-height: 0.8em;
                text-align: justify;
                text-justify: inter-cluster;
                text-align-last: justify;
            }
            
            span.weight_title:after
            {
                content: "";
                display: inline-block;
                width: 100%;
                font-size: 0;
                height: 0;
                line-height: 0;
                visibility: hidden;
            }
            
            .spec .weight
            {
                display: inline-block;
                text-align: center;
                width: 3.5em;
            }
            
            .mail_context #gbl_no
            {
                text-decoration: underline;
            }
            
            .mail_context p
            {
                text-align: justify;
            }
            
            .mail_context .underline_box
            {
                margin: 0px;
            }
            
            #mail_body
            {
                line-height: 2em;
            }
            
            #footer
            {
                width: 17em;
                float: right;
                margin-top: 1em;
                margin-right: -5em;
                text-align: left;
            }
            
            #footer .underline_box
            {
                margin-left: 0.7em;
                width: 85%
            }
            
            #footer #very_turly_yours
            {
                margin-bottom: 1.7em;
            }
            
            #footer .add_left_more
            {
                margin-left: 1.9em;
            }
            
            #footer #data_signed
            {
                margin-top: 2em;
                margin-left: 1.2em;
            }
        </style>
    </head>
    <body style="background-color: white;" onload="window.print();">
    	
    	<c:forEach var="powerOfAttorny" items="${powerOfAttornyList }" varStatus="status">
    		<c:if test="${not status.first }">
			<div style='page-break-after:always'></div>
			</c:if>
    	<c:set var="gbl" value="${powerOfAttorny.gbl }"/>
        <h1>POWER OF ATTORNEY</h1>
        <div id="mail">
            <div class="to_subject">
                <label for="textarea">To :</label>
                Chief of Customs<br />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Inchon / Pusan Customs Office<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Incon / Pusan, Korea
            </div>
            <div class="to_subject">
                Subject : Customs Clearance of Used Household Goods and Personal Effects
            </div>
            
            <div class="spec">
                <p><span class="weight_title">Name of Owner</span> : <c:out value="${gbl.rank}" /> <c:out value="${gbl.customerName}" />&nbsp;&nbsp;&nbsp;000 - 00 - <c:out value="${fn:substring(gbl.ssn, 5, 9)}"/></p>
                <p><span class="weight_title">Gross Weight</span> : <span class="weight"><c:out value="${gbl.lbs}" /></span>&nbsp;Lbs
                <p><span class="weight_title">Net Weight</span> : <span class="weight"><c:out value="${gbl.netWeight}" /></span>&nbsp;Lbs
                <p><span class="weight_title">Booked Vessel</span> : </p>
            </div>
            <div class="mail_context">
                <p class="header">Gentleman :</p>
                
                <p style="text-indent: 0.7em;" id="mail_body">I, the undersigned, authorize Youngjin Trade &amp; Transportation Co., Ltd. <c:out value="${company.address}" /> who represents<br><input type="text" class="underline_box" style="width: 100%"><br> U. S GBL carrier approved by Department of Defense of United States of America to process customs clearance for my househould goods moved under GBL number / Carrier's B/L number <span id="gbl_no"><c:out value="${gbl.no}" /></span> at Inchon / Pusan Customs Office, on my behalf.</p>
            </div>
            
            <div id="footer">
                <p id="very_turly_yours">Very turly yours,</p>
                <input type="text" class="underline_box">
                <br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;Customs Clearance Officer<br/>
                 &nbsp;&nbsp;&nbsp;&nbsp;United States Forces, Korea/<br/>
                 &nbsp;&nbsp;&nbsp;&nbsp;Property Owner
                <input type="text" class="underline_box">
                <p id="data_signed">Date signed</p>
            </div>
            
            <div style="clear: both; text-align: left; margin-left: -2em;">Youngjin Form</div>
        </div>
        </c:forEach>
        
    </body>
</html>