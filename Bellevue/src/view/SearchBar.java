package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import view.Size;

public class SearchBar extends HBox implements View{
	
	private SearchBox searchBox;
	private FilterBox filterBox;
	private OnSearchListener onSearchListener;
	private OnFilterListener onFilterListener;
	
	public SearchBar(){
		super();
		init();
	}
	
	public void init(){
		getStylesheets().add("/style/searchbarStylesheet.css");
		
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10));
		setSpacing(Size.SPACING);
		
		initSearchBox();
		initFilterBox();
	}
	
	public void initSearchBox(){
		searchBox = new SearchBox();
		getChildren().add(searchBox);
	}
	
	public void initFilterBox(){		
		filterBox = new FilterBox();
		getChildren().add(filterBox);
	}
	
	public void setOnSearchListener(OnSearchListener onSearchListener){
		this.onSearchListener = onSearchListener;
	}
	
	public void setOnFilterListener(OnFilterListener onFilterListener){
		this.onFilterListener = onFilterListener;
	}
	
	public interface OnSearchListener{
		public void onAction(String query);
	}
	
	public interface OnFilterListener{
		public void onAction(int which);
	}
	
	private class FilterBox extends HBox {
		private Label filterLbl;
		private ToggleGroup filterTglGrp;
		private RadioButton[] radioBtnArr;	// index 0: all, index 1: paid, index 2: unpaid
		private String[] radioStringArr;
		
		public FilterBox(){
			super();
			
			radioStringArr = new String[]{"All", "Paid", "Unpaid"};
			this.init();
		}
		
		private void init(){
			setAlignment(Pos.CENTER);
			setSpacing(Size.SPACING_RADIO_BTN);
			
			initFilterLbl();
			initFilterTglGrp();
			initRadioArr();
		}
		
		public void initFilterLbl(){
			filterLbl = new Label();
			filterLbl.setText("Filter");
			filterLbl.setId("filterLbl");
			getChildren().add(filterLbl);
		}
		
		public void initFilterTglGrp(){
			filterTglGrp = new ToggleGroup();
		}
		
		public void initRadioArr(){
			radioBtnArr = new RadioButton[radioStringArr.length];
			
			for(int i = 0; i < radioBtnArr.length; i++){
				radioBtnArr[i] = new RadioButton();
				radioBtnArr[i].setToggleGroup(filterTglGrp);
				radioBtnArr[i].setText(radioStringArr[i]);
				
				int temp = i;
				radioBtnArr[i].setOnAction(e -> {
					int which = temp;
					
					if(onFilterListener != null)
						onFilterListener.onAction(which);
				});
			}
			
			radioBtnArr[0].setSelected(true);
			
			getChildren().addAll(radioBtnArr);
		}
		
	}
	
	private class SearchBox extends HBox{
		private TextField searchTF;
		private Button searchBtn;
		private boolean searching;
		
		public SearchBox(){
			super();
			
			searching = false;
			
			this.init();
		}
		
		private void init(){
			setId("searchBox");
			
			setAlignment(Pos.CENTER);
			
			initSearchTF();
			initSearchBtn();
		}
		
		public void initSearchTF(){
			searchTF = new TextField();
			
			searchTF.setPromptText("Search");
			searchTF.setId("searchTF");
			
			searchTF.setOnKeyPressed(e -> {
				if(searchBtn != null){
					if(e.getCode() == KeyCode.ENTER){
						
						if(!searching){
							searching = true;
							searchBtn.setId("cancelBtn");
						}
						
						if(searchTF.getText().equals("") && searching){
							searching = false;
							searchBtn.setId("searchBtn");
						}
							
						search();
					}
				}
			});
			
			getChildren().add(searchTF);
		}
		
		public void initSearchBtn(){
			searchBtn = new Button("");
			
			searchBtn.setPrefSize(Size.BTN_PREF_HEIGHT, Size.BTN_PREF_HEIGHT);
			
			searchBtn.setId("searchBtn");
			
			searchBtn.setOnAction(e -> {
				if(onSearchListener != null && searching){
					
					searchBtn.setId("searchBtn");
					searchTF.setText("");
					
					search();
					
					searching = !searching;
				}
			});
			
			getChildren().add(searchBtn);
		}
		
		public void search(){
			onSearchListener.onAction(searchTF.getText());
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
