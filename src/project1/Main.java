package project1;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Word Break Problem");
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		
		ListView<String> table = new ListView<>();
		ListView<String> wordBreakAns = new ListView<>();
		Label lbl1 = new Label("Insert dictionary (spaces between words)");
		Label lbl2 = new Label("Insert word");
		TextField dictionaryTxt = new TextField();
		TextField wordTxt = new TextField();
		Button done = new Button("Done");
		Button clear = new Button("Clear");
		HBox buttons = new HBox();
		
		buttons.getChildren().add(done);
		buttons.getChildren().add(clear);
		grid.add(lbl1, 0, 0);
		grid.add(dictionaryTxt, 0, 1);
		grid.add(lbl2, 0, 2);
		grid.add(wordTxt, 0, 3);
		grid.add(table, 0, 4);
		grid.add(wordBreakAns, 1, 4);
		buttons.setAlignment(Pos.CENTER);
		border.setBottom(buttons);
		border.setCenter(grid);
		
		ArrayList<String> a = new ArrayList<>();
		
		done.setOnAction(e -> {
			table.getItems().clear();
			wordBreakAns.getItems().clear();
			String[] temp = dictionaryTxt.getText().split(" ");
			
			for(int i = 0; i < temp.length; i++) {
				a.add(temp[i]);
			}
			
			String arrans = "";
			String str = "";
			WordBreak word = new WordBreak(wordTxt.getText(), a);
			int [][] array = word.table();
			
			for(int i = 0; i < wordTxt.getText().length(); i++) {
				for(int j = 0; j < wordTxt.getText().length(); j++) {
					arrans += array[i][j];
				}
				arrans += "\n";
			}
			
			word.recWordBreak(a, wordTxt.getText(), array, str, 0, wordTxt.getText().length()-1);
			
			table.getItems().add(arrans);
			if(word.ans_word.length() == 0) {
				wordBreakAns.getItems().add("The string cant be broken using the provided dictonary");
			} else {
				wordBreakAns.getItems().add(word.ans_word);
			}
		});
		
		clear.setOnAction(e -> {
			dictionaryTxt.setText("");
			wordTxt.setText("");
			table.getItems().clear();
			wordBreakAns.getItems().clear();
		});

		Scene scene = new Scene(border, 496, 509);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}