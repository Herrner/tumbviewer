package com.nutrition.express.reblog;

import android.text.TextUtils;

import com.nutrition.express.model.rest.ApiService.ReblogService;
import com.nutrition.express.model.rest.ResponseListener;
import com.nutrition.express.model.rest.RestCallback;
import com.nutrition.express.model.rest.RestClient;
import com.nutrition.express.model.rest.bean.BaseBean;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by huang on 12/9/16.
 */

public class ReblogPresenter implements ReblogContract.Presenter, ResponseListener {
    private ReblogContract.View view;
    private Call<BaseBean<Void>> call;
    private ReblogService service;

    public ReblogPresenter(ReblogContract.View view) {
        this.view = view;
        service = RestClient.getInstance().getReblogService();
    }

    public void onAttach() {

    }

    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public void reblog(String blogName, String blogId, String blogkey, String blogType, String comment) {
        if (call == null) {
            HashMap<String, String> hashMap = new HashMap<>(4);
            hashMap.put("type", blogType);
            hashMap.put("id", blogId);
            hashMap.put("reblog_key", blogkey);
            if (!TextUtils.isEmpty(comment)) {
                hashMap.put("comment", comment);
            }
            call = service.reblogPost(blogName, hashMap);
            call.enqueue(new RestCallback<Void>(this, "reblog"));
        }
    }

    @Override
    public void onResponse(BaseBean baseBean, String tag) {
        if (view == null) {
            return;
        }
        call = null;
        view.onSuccess();
    }

    @Override
    public void onError(int code, String error, String tag) {
        if (view == null) {
            return;
        }
        call = null;
        view.onError(code, error);
    }

    @Override
    public void onFailure(Throwable t, String tag) {
        if (view == null) {
            return;
        }
        call = null;
        view.onFailure(t);
    }
}
