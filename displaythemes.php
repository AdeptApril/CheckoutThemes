<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript" >

	</script>
	</head>
	<body>
    //if($_POST['action'] == 'display') {
        <?php // Echo out the current contents of the theme choices
            Echo "The following are the current language choices for each machine at Sequoya: <br>";
            Echo "Checkout 1, language 3: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout1/lang3choice.txt" );
            Echo "<br>Checkout 1, language 4: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout1/lang4choice.txt" );
            Echo "<br>Checkout 2, language 3: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout2/lang3choice.txt" );
            Echo "<br>Checkout 2, language 4: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout2/lang4choice.txt" );
            Echo "<br>Checkout 3, language 3: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout3/lang3choice.txt" );
            Echo "<br>Checkout 3, language 4: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout3/lang4choice.txt" );
            Echo "<br>Checkout 4, language 3: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout4/lang3choice.txt" );
            Echo "<br>Checkout 4, language 4: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout4/lang4choice.txt" );
            Echo "<br>Checkout 5, language 3: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout5/lang3choice.txt" );
            Echo "<br>Checkout 5, language 4: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout5/lang4choice.txt" );
            Echo "<br>Checkout 6, language 3: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout6/lang3choice.txt" );
            Echo "<br>Checkout 6, language 4: ";
            echo file_get_contents( "http://www.checkoutthemes.com/sequoya/checkout6/lang4choice.txt" );
            Echo "<br>";
        ?>
    //}
	</body>
</html>
