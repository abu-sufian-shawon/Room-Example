package com.abu.sufian.roomexample.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.abu.sufian.roomexample.R;
import com.abu.sufian.roomexample.datamodel.StudentModel;
import com.abu.sufian.roomexample.room.DAOStudent;

public class InsertAsyncTask extends AsyncTask<StudentModel, Void, Boolean> {
    private Activity activity;
    private DAOStudent daoStudent;

    public InsertAsyncTask(Activity activity, DAOStudent daoStudent){
        this.daoStudent = daoStudent;
        this.activity = activity;
    }


    @Override
    protected Boolean doInBackground(StudentModel... studentModels) {
        daoStudent.insert(studentModels[0]);

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        EditText edtFirstName, edtLastName, edtEmail, edtPhone, edtInstitute;
        Spinner spnTechnology, spnSemester;

        edtFirstName = activity.findViewById(R.id.edtFirstName);
        edtLastName = activity.findViewById(R.id.edtLastName);
        edtPhone = activity.findViewById(R.id.edtPhone);
        edtEmail = activity.findViewById(R.id.edtEmail);
        edtInstitute = activity.findViewById(R.id.edtInstitute);
        spnTechnology = activity.findViewById(R.id.spnTechnology);
        spnSemester = activity.findViewById(R.id.spnSemester);

        edtFirstName.getText().clear();
        edtLastName.getText().clear();
        edtEmail.getText().clear();
        edtPhone.getText().clear();
        edtInstitute.getText().clear();
        spnSemester.setSelection(0);
        spnTechnology.setSelection(0);

    }

}
