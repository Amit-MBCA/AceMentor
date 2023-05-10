package com.example.ib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;

public class SendEmailContract extends ActivityResultContract<Intent, Boolean> {

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Intent input) {
        return input;
    }

    @Override
    public Boolean parseResult(int resultCode, @Nullable Intent intent) {
        return resultCode == Activity.RESULT_OK;
    }
}
