package uk.co.android.avernan.gamememory;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MemoryMainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memory_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ImageButton[] tiles = new ImageButton[4];
        private Handler handler = new Handler();

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_memory_main, container, false);

            // Get a reference to tiles
            tiles[0] = (ImageButton) rootView.findViewById(R.id.tile0);
            tiles[1] = (ImageButton) rootView.findViewById(R.id.tile1);
            tiles[2] = (ImageButton) rootView.findViewById(R.id.tile2);
            tiles[3] = (ImageButton) rootView.findViewById(R.id.tile3);
            // Set a Listener for each button
            for (int i = 0; i < tiles.length; ++i) {
                final int index = i;
                tiles[i].setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        tileUncovered(tiles[index]);
                    }
                });
            }
            return rootView;
        }

        private void tileUncovered(ImageButton tile) {
            // Set the image of the uncovered tile, i.e. flip the tile
            tile.setBackgroundResource(R.drawable.tile_arryn);
            // Call a reset method after a delay
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetTiles();
                }
            },1500);
        }

        private void resetTiles() {
            for(ImageButton tile : tiles) {
                tile.setBackgroundResource(R.drawable.tile_back);
            }
        }
    }
}
