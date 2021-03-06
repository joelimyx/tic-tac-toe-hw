package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TicTacToe mTicTacToe;
    EditText mPlayer1, mPlayer2;
    Button mButton;
    RecyclerView mRecyclerView;
    public static final int REQUEST_CODE = 9;
    WinnerAdapter adapter =  new WinnerAdapter(TicTacToe.getmPlayerWon());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent myIntent = new Intent(MainActivity.this,GameActivity.class);

        mTicTacToe = TicTacToe.getInstance();

        //Reference
        mPlayer1 = (EditText) findViewById(R.id.player_one_name);
        mPlayer2 = (EditText) findViewById(R.id.player_two_name);
        mButton = (Button) findViewById(R.id.start_game_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = mPlayer1.getText().toString();
                String name2 = mPlayer2.getText().toString();
                if (name1.isEmpty()){
                    mPlayer1.setError("Field cannot be empty");
                }
                if (name2.isEmpty()){
                    mPlayer2.setError("Field cannot be empty");
                }
                if (!name1.isEmpty() && !name2.isEmpty()){
                    myIntent.putExtra("player 1",name1);
                    myIntent.putExtra("player 2", name2);
                    startActivityForResult(myIntent,REQUEST_CODE);
                }
            }
        };
        mButton.setOnClickListener(onClickListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE){
            if (resultCode==RESULT_OK){
                if (!data.getStringExtra("winner").isEmpty()){
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
