import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class HIghscore {
	
	public ImageIcon icon = new ImageIcon("res/rabbit.png");
	public int score;
	public String highScore;

/*
 * follow the HIghscore constructor  
 */
	HIghscore(int gameScore){
		
		score = gameScore;
		highScore = this.ScoreOnFile();
		/*
		 * if no score exists, create one.
		 */
		if (highScore.equals("0"))
			highScore = "Nobody:0";
		
		CompareScores();
	
	}
	
	
	
	
	
	private String ScoreOnFile() {
		FileReader readFile = null;
		BufferedReader reader = null;
	
		/*
		 * locate highscore.txt and return first line of text in file
		 */
		try
		{
			readFile = new FileReader("highscore.txt");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		catch (Exception e)
		{
			/*
			 * return "0" if no score exists
			 */
			return "0";
		}
		finally
		{
			try{
				/*
				 * if the reader dosn't return null, close it.
				 */
				if (reader != null)
					reader.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	
	
	
	
	
	private void CompareScores(){
		
		/*
		 * check score with the integer in highScore.txt
		 */
		if (score > Integer.parseInt(highScore.split(":")[1]))
		{
			String name = JOptionPane.showInputDialog("Highscore! Enter your name: ");
					
			highScore = name + ":" + score;
			/*
			 * create a newFile over the old one	
			 */
			File scoreFile = new File ("highscore.txt");
			/*
			 * if the file does not alreadyt\ exist create it
			 */
			if (!scoreFile.exists())
			{
				try{
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try
			{
				writeFile = new FileWriter (scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
			}
			catch (Exception e)
			{
				
			}
			finally{
				try{
					if (writer != null)
						writer.close();
				} catch (Exception e){}
				
			}	
		}
	}
	
	private String GetHighScore(){
		return highScore;
		
	}


public static void main(String[] args){
	HIghscore hs = new HIghscore(76);
	
	
	
	
	
	}
}