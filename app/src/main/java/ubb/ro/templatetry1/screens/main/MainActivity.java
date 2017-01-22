package ubb.ro.templatetry1.screens.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ubb.ro.templatetry1.R;
import ubb.ro.templatetry1.TemplateApp;
import ubb.ro.templatetry1.data.component.DaggerMainScreenComponent;
import ubb.ro.templatetry1.data.module.MainScreenModule;
import ubb.ro.templatetry1.model.Event;
import ubb.ro.templatetry1.util.DialogUtils;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Inject this component
        final TemplateApp app = (TemplateApp) getApplication();
        DaggerMainScreenComponent.builder()
                .netComponent(app.getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainPresenter.loadEvents();
    }


    @Override
    public void showEvents(List<Event> events) {
        for (Event e : events) {
            Log.d(TAG, e.toString());
        }
        runOnUiThread(() -> {
            EventAdapter adapter = new EventAdapter(events);
            mRecyclerView.setAdapter(adapter);
        });

    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
        DialogUtils.showError(this, message);
    }

    @Override
    public void showComplete() {
        Log.d(TAG, "Completed lading events");
    }

    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

        private List<Event> events;

        public EventAdapter(List<Event> events) {
            this.events = events;
        }

        @Override
        public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
            final Event event = events.get(position);
            holder.mItemTextView.setText(event.toString());
        }

        @Override
        public int getItemCount() {
            return events.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.rv_item)
            TextView mItemTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
