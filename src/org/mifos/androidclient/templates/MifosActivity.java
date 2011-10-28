/*
 * Copyright (c) 2005-2011 Grameen Foundation USA
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.androidclient.templates;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.mifos.androidclient.R;
import org.mifos.androidclient.util.MifosConstants;
import org.mifos.androidclient.util.ui.UIUtils;

public abstract class MifosActivity extends Activity {

    protected UIUtils mUIUtils;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mUIUtils = new UIUtils(this);
    }

    public final static String MIFOS_APPLICATION_PREFERENCES = "MifosApplicationPreferences";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeServerAddress:
                mUIUtils.promptForTextInput(getString(R.string.dialog_server_address), new UIUtils.DialogCallbacks() {

                    @Override
                    public void onCommit(Object inputData) {
                        SharedPreferences settings = getSharedPreferences(MIFOS_APPLICATION_PREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(MifosConstants.MIFOS_SERVER_ADDRESS_KEY, (String) inputData);
                        editor.commit();
                        mUIUtils.displayLongMessage(getString(R.string.toast_address_set));
                    }

                    @Override
                    public void onCancel() { }

                });
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
