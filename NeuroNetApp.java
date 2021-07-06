package application;

//--module-path D:\JFX\JFX\javafx-sdk-13.0.2\lib --add-modules=javafx.controls
import java.util.Random;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class NeuroNetApp extends Application{
	public static void main(String[] args){
		launch();
	}
public void start(Stage MyStage){
	MyStage.setTitle("Just cause I can");
	AnchorPane A=new AnchorPane();
	Scene S=new Scene(A,1000,600);
	MyStage.setScene(S);
	MyStage.setResizable(false);
	
	
	Button I=new Button("NN initialization ");
	Button L=new Button("NN learning");
	Button St=new Button("NN start");
    StringBuffer Str=new StringBuffer("Result is: "+"\n");
	Label Lb=new Label();
	ScrollPane ScrPn=new ScrollPane(Lb);
	ScrPn.setPrefViewportWidth(986);
	ScrPn.setPrefViewportHeight(295);
	
	Button C1=new Button("Circumstance 1 (Good)");
	Button C2=new Button("Circumstance 2 (Bad)");
	Button C3=new Button("Circumstance 3 (Good)");
	Label C11=new Label("None");
	Label C22=new Label("None");
	Label C33=new Label("None");
	Label R=new Label("Result");
	
	AnchorPane.setTopAnchor(C1, 50.0);
	AnchorPane.setRightAnchor(C1, 670.0);
	AnchorPane.setLeftAnchor(C1, 0.0);
	AnchorPane.setTopAnchor(C2, 100.0);
	AnchorPane.setRightAnchor(C2, 670.0);
	AnchorPane.setLeftAnchor(C2, 0.0);
	AnchorPane.setTopAnchor(C3, 150.0);
	AnchorPane.setRightAnchor(C3, 670.0);
	AnchorPane.setLeftAnchor(C3, 0.0);
	AnchorPane.setTopAnchor(R, 100.0);
	AnchorPane.setRightAnchor(R, 0.0);
	AnchorPane.setLeftAnchor(R, 670.0);
    
    AnchorPane.setTopAnchor(C11, 50.0);
    AnchorPane.setRightAnchor(C11, 340.0);
    AnchorPane.setLeftAnchor(C11, 340.0);
    AnchorPane.setTopAnchor(C22, 100.0);
    AnchorPane.setRightAnchor(C22, 340.0);
    AnchorPane.setLeftAnchor(C22, 340.0);
    AnchorPane.setTopAnchor(C33, 150.0);
    AnchorPane.setRightAnchor(C33, 340.0);
    AnchorPane.setLeftAnchor(C33, 340.0);

    AnchorPane.setTopAnchor(I, 0.0);
    AnchorPane.setRightAnchor(I, 670.0);
    AnchorPane.setLeftAnchor(I, 0.0);
    
    AnchorPane.setTopAnchor(L, 0.0);
    AnchorPane.setRightAnchor(L, 340.0);
    AnchorPane.setLeftAnchor(L, 340.0);
    
    AnchorPane.setTopAnchor(St, 0.0);
    AnchorPane.setRightAnchor(St, 0.0);
    AnchorPane.setLeftAnchor(St, 670.0);
    
    AnchorPane.setTopAnchor(ScrPn, 290.0);
    AnchorPane.setLeftAnchor(ScrPn, 0.0);
    
	A.getChildren().addAll(I,L,St,ScrPn,C1,C2,C3,C11,C22,C33,R);
	
	MyStage.show();

	Random ran=new Random();
	int i=3,h1=2;
	
	double IN[]=new double[i];
	double HN1[]=new double[h1];
	
	double IN_HN1_Link[][]=new double[i][h1];
	double HN1_ON[]=new double[h1];
    
	I.setOnAction(new EventHandler <ActionEvent>(){
		public void handle(ActionEvent ae){
			Lb.setText(Str.toString());
			for(int x=0;x<i;x++){
				for(int y=0;y<h1;y++){
					IN_HN1_Link[x][y]=(1*ran.nextDouble());
					Str.append("Link between input neuron "+(x+1)+" and neuron of first hidden layer "+(y+1)+" equals to "+IN_HN1_Link[x][y]+"\n");
					Lb.setText(Str.toString());
				}
			}	
			for(int x=0;x<h1;x++){
				HN1_ON[x]=(1*ran.nextDouble());
				Str.append("Link between neuron of first hidden layer "+(x+1)+" and output neuron equals to "+HN1_ON[x]+"\n");
				Lb.setText(Str.toString());	
			}}});
	
	L.setOnAction(new EventHandler <ActionEvent>(){
		public void handle(ActionEvent ae){
			int NormalMassive[][]={
					{1, 1, 1, 1},
					{1, 0, 0, 1},
					{0, 1, 0, 0},
					{0, 0, 1, 1},
					{1, 1, 0, 0},
					{0, 1, 1, 0},
					{1, 0, 1, 1},
			};

			double ON=0.0000001;
			double suc=0;
		    int k=0,j=100;
			int l=0,p=0,a=0,b0=0,b=0,b1=0,n=0,b2=0,b3=0,n1=0,b4=0,b5=0;
			for(;k<j;k++){
				Str.append("\n"+"\n");
				Lb.setText(Str.toString());
				for(l=0;l<7;l++){
					for(p=0;p<3;p++){
						IN[p]=NormalMassive[l][p];
					}
					//Body of NN-work
					for(b0=0;b0<h1;b0++){
						HN1[b0]=0;
					}
					ON=0;
					for(a=0;a<i;a++){
						for(b=0;b<h1;b++){
							HN1[b]=HN1[b]+(IN[a]*IN_HN1_Link[a][b]);
						}
					}
						for(b1=0;b1<h1;b1++){
							ON=ON+(HN1[b1]*HN1_ON[b1]);
						}
						if(ON>=1)
							ON=1;
						else
							ON=0;
						Str.append("Era "+(k+1)+" "+"Combination "+(l+1)+": ");
						Lb.setText(Str.toString());
					//Check of ON value
						switch(NormalMassive[l][3]){
						case 1:{
							if(ON==NormalMassive[l][3]){
								Str.append("Sucñess"+"\n");
								Lb.setText(Str.toString());
								suc=suc+1;
								break;
							}
							else{
								Str.append("Fail"+"\n");
								Lb.setText(Str.toString());
								//Call method of error correction
								for(n=0;n<i;n++){
								switch(NormalMassive[l][n]){
								case 1:{
									for(b2=0;b2<h1;b2++){
										IN_HN1_Link[n][b2]=IN_HN1_Link[n][b2]+0.1;
									}
									break;
								}
								case 0:{
									for(b3=0;b3<h1;b3++){
										IN_HN1_Link[n][b3]=IN_HN1_Link[n][b3]-0.1;
									}
									break;
								}
								}
								}
							}
							break;
						}
						case 0:{
							if(NormalMassive[l][3]==ON){
								Str.append("Sucñess"+"\n");
								Lb.setText(Str.toString());
								suc=suc+1;
							break;
							}
							else{
								Str.append("Fail"+"\n");
								Lb.setText(Str.toString());
								//Call method of error correction
								for(n1=0;n1<i;n1++){
									switch(NormalMassive[l][n1]){
									case 1:{
										for(b4=0;b4<h1;b4++){
											IN_HN1_Link[n1][b4]=IN_HN1_Link[n1][b4]-0.1;
										}
										break;
									}
									case 0:{
										for(b5=0;b5<h1;b5++){
											IN_HN1_Link[n1][b5]=IN_HN1_Link[n1][b5]+0.1;
										}
										break;
									}
									}
									}
							}
							break;
						}
						}
				}
			}
		}});
	
	C1.setOnAction(new EventHandler <ActionEvent>(){
		public void handle(ActionEvent ae){	
			String j;
			if(IN[0]==1.0){
				IN[0]=0;
			j=("NO");}
			else{
				IN[0]=1;
				j=("YES");
			}
		C11.setText(j);
		}});
	C2.setOnAction(new EventHandler <ActionEvent>(){
		public void handle(ActionEvent ae){	
			String j;
			if(IN[1]==1.0){
				IN[1]=0;
				j=("NO");}
			else{
				IN[1]=1;
				j=("YES");
			}			
			C22.setText(j);
		}});
	C3.setOnAction(new EventHandler <ActionEvent>(){
		public void handle(ActionEvent ae){	
			String j;
			if(IN[2]==1.0){
				IN[2]=0;
	         j=("NO");}
			else{
				IN[2]=1;
			 j=("YES");
			}			
			C33.setText(j);
		}});
	
	St.setOnAction(new EventHandler <ActionEvent>(){
		public void handle(ActionEvent ae){	
			double ON=0.0000001;
			int a=0,b0=0,b=0,b1=0;
			for(b0=0;b0<h1;b0++){
				HN1[b0]=0;
			}
			ON=0;
			for(a=0;a<i;a++){
				for(b=0;b<h1;b++){
					HN1[b]=HN1[b]+(IN[a]*IN_HN1_Link[a][b]);
				}
			}
				for(b1=0;b1<h1;b1++){
					ON=ON+(HN1[b1]*HN1_ON[b1]);
				}
				if(ON>=1)
					ON=1;
				else
					ON=0;
				Str.append("ON = "+ON+"\n");
				Lb.setText(Str.toString());
				
				
				String g;
				if(ON==1.0){
		         g=("YES");}
				else{
				 g=("NO");}			
				R.setText(g);
		}});
	

}
}