<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript" >

	</script>
	</head>
	<body>
		<form id="themechoice" method="POST">
            <label for="library">Library</label>
			<select id="library">
				<option>Sequoya</option>
			</select><br>
            <label for="machineNum">Machine Number</label>
			<select id="machineNum">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
			</select><br>
            <label for="languageNum">Language Number</label>
			<select id="languageNum">
				<option>3</option>
				<option>4</option>
			</select><br>
            <label for="theme">Theme</label>
			<select id="theme">
                <option>Storybook Clay</option>
				<option>Storybook Nicky</option>
			</select><br>
			<input type="button" onclick="submit()" value="Submit">
			<input type="button" onclick="reset()" value="Reset"><br>
		</form>
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
		
		<script>
		function reset() {
    	document.getElementById("themechoice").reset();
		}
		function submit() {
    	document.getElementById("themechoice").submit();
        }
        document.themechoice.onsubmit() {
        <?php
        //$filename = "checkoutthemes.com/sequoya/checkout".machineNum."/lang".languageNum."choice.txt";
        //file_put_contents($filename, "http://www.checkoutthemes.com/sequoya/InstallStorybookClayThemeSequoya.exe");
        echo "test";
        ?>
		}
		</script>
	</body>
</html>
