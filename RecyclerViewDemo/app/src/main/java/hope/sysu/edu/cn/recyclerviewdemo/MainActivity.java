package hope.sysu.edu.cn.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

/*    private RecyclerView rv;
    private String[] data = {"hello", "world, ", "dengzhirong", "haha"};*/
    private RecyclerView rv;
    private List<String> mDatas;
    private mSimpleAdapter mAdapter;

/*    private ImageLoader imageLoader;
    private ImageView image_loader;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        imageLoader = ImageLoader.getInstance();
        image_loader = (ImageView) findViewById(R.id.image_loader);
        imageLoader.displayImage("http://blog.cgsdream.org/content/images/2015/05/me.jpg", image_loader);
*/


        initDatas();

        initViews();

        mAdapter = new mSimpleAdapter(MainActivity.this, mDatas);
        rv.setAdapter(mAdapter);

       rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //


/*        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            *//**
             * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
             * Any content drawn by this method will be drawn before the item views are drawn,
             * and will thus appear underneath the views.
             *
             * @param c      Canvas to draw into
             * @param parent RecyclerView this ItemDecoration is drawing into
             * @param state  The current state of RecyclerView
             *//*
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });*/

        // 设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);



        // 设置RecyclerView动画
        rv.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new mSimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "short Click: " + position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "long Click: " + position + "", Toast.LENGTH_SHORT).show();
            }
        });

/*        rv = new RecyclerView(this);
        setContentView(rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter() {

            class ViewHolder extends RecyclerView.ViewHolder{

                private TextView tv;

                public ViewHolder(View itemView) {
                    super(itemView);

                    tv = (TextView) itemView;
                }

                public TextView getTv() {
                    return tv;
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(new TextView(parent.getContext()));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
                vh.getTv().setText(data[position]);
            }

            @Override
            public int getItemCount() {
                return data.length;
            }
        });*/


/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for(int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char)i);
        }

    }
    private void initViews() {
        rv = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.addItem:
                mAdapter.addData(1);
                break;
            case R.id.deleteItem:
                mAdapter.deleteData(1);
                break;
            case R.id.listView_btn:
                rv.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.gridView_btn:
                rv.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.horizontal_btn:
                rv.setLayoutManager(new StaggeredGridLayoutManager(5,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.staggered_btn:
                rv.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
