package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;
    private int Gap = 50;
    private int sGap = 400;
    private int tbPad = 5;
    
    //buttons and containers are instantiated
    Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container southContainer = new Container(new FlowLayout());
    Toolbar myToolbar = new Toolbar();
    Button switchButton = new Button(); 
    Button accelButton = new Button();		
    Button brakeButton = new Button();
    Button rightButton = new Button();
    Button leftButton = new Button();
    Button exitButton = new Button();
    Button playButton = new Button();
    CheckBox soundBox = new CheckBox();
    Button changePosition = new Button();
    Label title = new Label("Race Car Game");
    UITimer timer = new UITimer(this);
    BGSound myBGSound = new BGSound("canton_Lewd-Day-160.mp3");
    CarCollisionSound myCarCollisionSound = new CarCollisionSound("CARCR111.WAV");
    FuelPickupSound myFuelPickupSound = new FuelPickupSound("slurp.wav");
    LifeEndSound myLifeEndSound = new LifeEndSound("Crash.aif");
    public Game(){
          gw = new GameWorld(this, myCarCollisionSound, myFuelPickupSound, myLifeEndSound); //create Observable GameWorld
          gw.init();			//initialize world
          mv = new MapView(gw.getCollection(), gw);	//create and Observer for the map
          sv = new ScoreView(gw); //create an observer for the game state data
          gw.addObserver(mv);	//register the map observer
          gw.addObserver(sv);	//register the score observer

           // code here to create Command objects for each command,
           // add commands to side menu and title bar area, bind commands to keys, create
           // control containers for the buttons, add buttons to the control containers,
           // add commands to the buttons, and add control containers, MapView, and
           // ScoreView to the form
          
          
          //tool bar is set
          setToolbar(myToolbar); 
          
          //Command Objects are created 
          PlayCommand myPlayCommand = new PlayCommand(this, playButton);
          AccelCommand myAccelCommand = new AccelCommand(gw);
          SwitchCommand mySwitchCommand = new SwitchCommand();
          BrakeCommand myBrakeCommand = new BrakeCommand(gw);
          RightCommand myRightCommand = new RightCommand(gw);
          LeftCommand myLeftCommand = new LeftCommand(gw);
          ExitCommand myExitCommand = new ExitCommand();
          Help myHelpCommand = new Help();
          SoundCommand mySoundCommand = new SoundCommand(gw, soundBox, myBGSound);
          AboutCommand myAboutCommand = new AboutCommand();
          PositionCommand myPositionCommand = new PositionCommand(gw);
          
          //Command Objects are assigned to buttons
          accelButton.setCommand(myAccelCommand);
          switchButton.setCommand(mySwitchCommand); 
          brakeButton.setCommand(myBrakeCommand);
          rightButton.setCommand(myRightCommand);
          leftButton.setCommand(myLeftCommand);
          exitButton.setCommand(myExitCommand);
          soundBox.setCommand(mySoundCommand);
          playButton.setCommand(myPlayCommand);
          changePosition.setCommand(myPositionCommand);
          
          //key listeners are added to certain commands
          addKeyListener('b', myBrakeCommand);
          addKeyListener('l', myLeftCommand);
          addKeyListener('r', myRightCommand);
          addKeyListener('a', myAccelCommand);
          addKeyListener('x', myExitCommand);
          
          //Styling on buttons and containers
          switchButton.getAllStyles().setMargin(Component.TOP, Gap);
          switchButton.getAllStyles().setPadding(Component.TOP, tbPad);
          switchButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);
          
          accelButton.getAllStyles().setPadding(Component.TOP, tbPad);
          accelButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);
          
          brakeButton.getAllStyles().setPadding(Component.TOP, tbPad);
          brakeButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);
          
          rightButton.getAllStyles().setPadding(Component.TOP, tbPad);
          rightButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);
          
          leftButton.getAllStyles().setPadding(Component.TOP, tbPad);
          leftButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);
          
          exitButton.getAllStyles().setPadding(Component.TOP, tbPad);
          exitButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);
          
          playButton.getAllStyles().setMargin(Component.LEFT, sGap);
          playButton.getAllStyles().setPadding(Component.TOP, tbPad);
          playButton.getAllStyles().setPadding(Component.BOTTOM, tbPad);         
          playButton.getAllStyles().setBgTransparency(255);
          playButton.getAllStyles().setBgColor(ColorUtil.BLUE);
          playButton.getAllStyles().setFgColor(ColorUtil.WHITE);
          
          changePosition.getAllStyles().setPadding(Component.TOP, tbPad);
          changePosition.getAllStyles().setPadding(Component.BOTTOM, tbPad); 
                    
         
          switchButton.getUnselectedStyle().setBgTransparency(255);
          switchButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
          switchButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
          
          accelButton.getUnselectedStyle().setBgTransparency(255);
          accelButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
          accelButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
          
          brakeButton.getUnselectedStyle().setBgTransparency(255);
          brakeButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
          brakeButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
          
          rightButton.getUnselectedStyle().setBgTransparency(255);
          rightButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
          rightButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
          
          leftButton.getUnselectedStyle().setBgTransparency(255);
          leftButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
          leftButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
          
          exitButton.getUnselectedStyle().setBgTransparency(255);
          exitButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
          exitButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
          
          
          soundBox.getAllStyles().setBgTransparency(255);
          soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
          mySoundCommand.putClientProperty("SideComponent", soundBox);

          
          westContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
          southContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
          mv.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
          sv.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

          
          //buttons are added to respective containers
          this.setLayout(new BorderLayout());
          westContainer.add(switchButton);
          westContainer.add(accelButton);
          westContainer.add(brakeButton);
          westContainer.add(rightButton);
          westContainer.add(leftButton);
          westContainer.add(exitButton);
          
          southContainer.add(playButton);
          southContainer.add(changePosition);
          
          //components are added to tool bar
          myToolbar.addCommandToRightBar(myHelpCommand);
          myToolbar.addCommandToSideMenu(myAccelCommand);
          myToolbar.addCommandToSideMenu(myAboutCommand);
          myToolbar.addCommandToSideMenu(myExitCommand);
          myToolbar.addCommandToSideMenu(mySoundCommand);

          myToolbar.setTitle("Race Car Game");

           //containers are added to specific regions in the parent container
          this.add(BorderLayout.CENTER, mv);
          this.add(BorderLayout.NORTH, sv);
          this.add(BorderLayout.WEST, westContainer);
          this.add(BorderLayout.SOUTH, southContainer);
          
          //show content pane
          this.show();
    }
    
    public void changePlayName(int i){
    	if(i == 1){
    		playButton.setName("Pause");
    		timer.schedule(20, true, this);
    	}
    	else{
    		playButton.setName("Play");
    		timer.cancel();
    	}
    }
    public void run(){
    	gw.tick();
    }
}
