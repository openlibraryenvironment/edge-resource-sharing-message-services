curl -X POST -H "Accept: application/xml" -H 'Content-type: application/xml' -d '<ns1:ISO18626Message ns1:version="1.2" xmlns:ns1="http://illtransactions.org/2013/iso18626">
  <ns1:request>
    <ns1:header>
      <ns1:requestingAgencyRequestId>TESTCASE002f9c54549-397e-4b4d-bea8-8ab9f5fe1d2c</ns1:requestingAgencyRequestId>
    </ns1:header>
    <ns1:bibliographicInfo>
      <ns1:title>A title</ns1:title>
      <ns1:author>The Author</ns1:author>
      <ns1:subtitle>A subtitle</ns1:subtitle>
    </ns1:bibliographicInfo>
  </ns1:request>
</ns1:ISO18626Message>
' http://localhost:8080/iso18626
