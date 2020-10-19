package com.xmcu.mynews.model;

public interface IModel {

    interface AsyncCallback {
        /**
         * 处理成功
         *
         * @param success 返回的信息
         */
        void onSuccess(Object success);

        /**
         * 处理出错
         *
         * @param error 错误信息
         */
        void onError(Object error);

    }
}
