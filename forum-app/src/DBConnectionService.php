<?php

  error_reporting(0);  

  //Connect to Server
  $conn_string = "host=127.0.0.1 port=5432 dbname=forumapp_database user=forumapp password=12345678";
  $dbconn = pg_connect($conn_string)
    or die("Could not connect");

  if(array_key_exists('query', $_POST))
  {
    $statement = $_POST['query'];
    $qry_result = pg_query($dbconn, $statement);
    
    pg_close($dbconn); //close db connection

    if (!empty($qry_result )) {
       //$response["success"] = 1;
       //echo json_encode($response);
       //echo "\n";
       
       while ( $row = pg_fetch_assoc( $qry_result )) 
       {
         echo json_encode( $row );
         echo "\n";
       }
    }
    else
    {
      // error
      $response["success"] = 0;
      $response["message"] = "no result!!!!";
      // echo no users JSON
      echo json_encode($response);
    }
  
  }
  
  if(array_key_exists("insert", $_POST))
  {
    $statement = $_POST['insert'];
    if($res = pg_query($dbconn, $statement))
    {
      if ($row = pg_fetch_assoc($res)) 
      {
        $row_keys = array_keys($row); 
        $id_key = $row_keys[0];
        $id_value = $row[$id_key];
      }
      $response["success"] = 1;
      $response[$id_key] = (int)$id_value;
      echo json_encode($response);
      echo "\n";
    }
    else
    {
      // Error Code list is found at http://www.postgresql.org/docs/8.1/static/errcodes-appendix.html
      $error_code = pg_result_error_field($dbconn, PGSQL_DIAG_SQLSTATE) ;
      $error_string = pg_last_error ($dbconn);
      // if($error_code == UNIQUE_VIOLATION)
      if(strstr($error_string, "duplicate key value violates unique constraint"))
      {
        $response["success"] = 0;
        $response["error_code"] = -1;
        $response["message"] = $error_string;
        echo json_encode($response);
        echo "\n";
      }
      else
      {
        $response["success"] = 0;
        $response["error_code"] = 0;
        $response["message"] = $error_string;
        echo json_encode($response);
        echo "\n";
      }
    }
    pg_close($dbconn); //close db connection
  }

  if(array_key_exists("update", $_POST))
  {
    $statement = $_POST['update'];
    if($res = pg_query($dbconn, $statement))
    {
      $response["success"] = 1;
      echo json_encode($response);
    }
    else
    {
      // Error Code list is found at http://www.postgresql.org/docs/8.1/static/errcodes-appendix.html
      $error_code = pg_result_error_field($dbconn, PGSQL_DIAG_SQLSTATE) ;
      $error_string = pg_last_error ($dbconn);
      if($error_code == UNIQUE_VIOLATION)
      {
        $response["success"] = 0;
        $response["error_code"] = -1;
        $response["message"] = $error_string;
        echo json_encode($response);
      }
      else
      {
        $response["success"] = 0;
        $response["error_code"] = 0;
        $response["message"] = $error_string;
        echo json_encode($response);
      }
    }
    pg_close($dbconn); //close db connection
  }


  if(array_key_exists("delete", $_POST))
  {
    $statement = $_POST['delete'];
    if($res = pg_query($dbconn, $statement))
    {
      $response["success"] = 1;
      echo json_encode($response);
      echo "\n";
    }
    else
    {
      // Error Code list is found at http://www.postgresql.org/docs/8.1/static/errcodes-appendix.html
      $error_code = pg_result_error_field($dbconn, PGSQL_DIAG_SQLSTATE) ;
      $error_string = pg_last_error ($dbconn);
      $response["success"] = 0;
      $respone["error_code"] = 0;
      $response["message"] = $error_string;
      echo json_encode($response);
    }
    pg_close($dbconn); //close db connection
  }


  
  
?>
