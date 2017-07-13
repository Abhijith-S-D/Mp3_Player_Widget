package AppPackage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MainClass 
{
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public Player player;
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    
    public void stop()
    {
        if(player != null)
        {
            player.close();
            pauseLocation=0;
            songTotalLength=0;
            MP3PlayerGUI.jLabel1.setText("");
        }
    }
    
    public void pause()
    {
        if(player != null)
        {
            try {
                pauseLocation=FIS.available();
                player.close();
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void resume()
    {
        if(FIS!=null){
        try {
            MP3PlayerGUI.jLabel1.setText(MP3PlayerGUI.name);
            FIS=new FileInputStream(fileLocation);
            BIS=new BufferedInputStream(FIS);
            player=new Player(FIS);
            FIS.skip(songTotalLength - pauseLocation);
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         new Thread()
    {
        @Override
        public void run()
        {
            try {
                player.play();
            } catch (JavaLayerException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }.start();
        }
    }
    
    public void play(String path)
    {
        try {
            FIS=new FileInputStream(path);
            BIS=new BufferedInputStream(FIS);
            songTotalLength=FIS.available();
            fileLocation=path+"";
            player=new Player(FIS);
        } catch (FileNotFoundException | JavaLayerException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
         new Thread()
    {
        @Override
        public void run()
        {
            try {
                player.play();
                if(player.isComplete()&&MP3PlayerGUI.count==1)
                {
                    play(fileLocation);
                }
                if(player.isComplete())
                {
                    MP3PlayerGUI.jLabel1.setText("");
                }
            } catch (JavaLayerException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }.start();
        
    }
   
}
