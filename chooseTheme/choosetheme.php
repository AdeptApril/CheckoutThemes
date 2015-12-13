<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript" >

	</script>
	</head>
	<body>
        <?php // Echo out the current contents of the theme choices
            $machineNum = $_POST['machineNum'];
            $languageNum = $_POST['languageNum'];
            $theme = $_POST['theme'];
            $filename = "../sequoya/checkout".$machineNum."/lang".$languageNum."choice.txt";
            file_put_contents($filename, $theme);
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
		
		<script>
		function reset() {
    	document.getElementById("themechoice").reset();
		}
		function submit() {
    	document.getElementById("themechoice").submit();
        }
        document.themechoice.onsubmit() {
        <?php
        $filename = "checkoutthemes.com/sequoya/checkout".machineNum."/lang".languageNum."choice.txt";
        file_put_contents($filename, "http://www.checkoutthemes.com/sequoya/InstallStorybookTestThemeSequoya.exe");
        echo "test";
        ?>
		}
		</script>
	</body>
</html>
