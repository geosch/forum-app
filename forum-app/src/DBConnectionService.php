<?php
	$query = $_POST['query'];

		//Connect to Server
	$conn_string = "host=127.0.0.1 port=5432 dbname=forumapp_database user=forumapp password=12345678";
	$dbconn = pg_connect($conn_string)
		or die("Could not connect");

	$qry_result = pg_query($dbconn, $query)
		or die("Could not query");

	pg_close($dbconn); //close db connection
		
		// echoing JSON response
    echo json_encode($qry_result);
?>