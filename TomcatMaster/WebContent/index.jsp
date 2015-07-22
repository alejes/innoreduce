<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Big Data Innopolis Group">
    <link rel="icon" href="favicon.ico">
	<title>Innoreduce alpha 1.1</title>
	<!--<link rel="stylesheet" href="bootstrap.min.css">-->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script type="text/javascript" src="script.js"></script>
</head>
<body>

<body role="document">	
	<div class="row">
		<div class="col-sm-4">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Select servers</h3>
				</div>
				<div class="panel-body">
					<span id = "insertServers">
						<script type="text/javascript">
							serversShow();
						</script>
					</span>
					<form class="navbar-form navbar-left" role="search"  action="actions" method="GET">
						<div class="form-group">
							<input type="text" class="form-control" size = "25"  name="addip" placeholder="Add ip address, e.g. 127.0.0.11">
						</div>
						<input type="hidden" name="action" value="addip">
						<button type="submit" class="btn btn-default" onClick="addServer();" onsubmit="return(false);">Add</button>
					</form>
				</div>
			</div>
        </div><!-- /.col-sm-4 -->
		<div class="col-sm-4">
			<div class="form-group">
				<label for="comment">Please enter your script:</label>
				<textarea class="form-control" rows="5" id="inputScript" placeholder="avg h"></textarea>
				<button type="button" class="btn btn-lg btn-primary"  onclick="sendRequest();" >Execute</button>
			</div>
			<div class="alert alert-info" role="alert" id="sendingLabel" style="display:none">
				Sending request, please wait!
			</div>
			<div class="alert alert-success" role="alert" id="answerLabel"  style="display:none">
				
			</div>
		</div>
	</div>
</body>
</html>