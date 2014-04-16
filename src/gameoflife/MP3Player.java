package gameoflife;

import javazoom.jl.player.advanced.*;

class MP3Player extends PlaybackListener implements Runnable {
  
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;    

    public MP3Player(String filePath)
    {
        this.filePath = filePath;
    }

    public void play()
    {
        try
        {
            String urlAsString = "file:///"+ new java.io.File(".").getCanonicalPath()+ "/"+ this.filePath;
            this.player = new AdvancedPlayer(new java.net.URL(urlAsString).openStream(),
                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
            );
            this.player.setPlayBackListener(this);
            this.playerThread = new Thread(this, "AudioPlayerThread");
            this.playerThread.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    } // end play method

    public void stop() {
      this.playerThread.stop();
    }
    
    @Override
    public void run()
    {
        try
        {
            this.player.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex)
        {
            ex.printStackTrace();
        }

    }
}  