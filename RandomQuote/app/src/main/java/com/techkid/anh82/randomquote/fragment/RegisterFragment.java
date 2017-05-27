package com.techkid.anh82.randomquote.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techkid.anh82.randomquote.R;
import com.techkid.anh82.randomquote.activities.MainActivity;
import com.techkid.anh82.randomquote.network.RegisterRequest;
import com.techkid.anh82.randomquote.network.RegisterResponse;
import com.techkid.anh82.randomquote.network.RetrofitFactory;
import com.techkid.anh82.randomquote.network.services.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {


    private static final String TAG = "SignupActivity";

    private EditText _usernameText;
    private EditText _passwordText;
    private EditText _reEnterPasswordText;
    private Button _signupButton;
    private TextView _loginLink;

    public RegisterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        _usernameText = (EditText) view.findViewById(R.id.input_email);
        _passwordText = (EditText) view.findViewById(R.id.input_password);
        _reEnterPasswordText = (EditText) view.findViewById(R.id.input_reEnterPassword);
        _signupButton = (Button) view.findViewById(R.id.btn_signup);
        _loginLink = (TextView) view.findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Finish the registration screen and return to the Login activity
                ((MainActivity)getActivity()).changeFragment(new LoginFragment(),true);

            }
        });
        return view;
    }

    public void register(){
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();
        RegisterService registerService = RetrofitFactory.getInstence().createService(RegisterService.class);
        registerService.register(new RegisterRequest(username,password)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.code()==200) {
                    RegisterResponse registerResponse = response.body();
                    if(registerResponse.getCode()==1){
                        Toast.makeText(getActivity(),"Resgisted",Toast.LENGTH_SHORT).show();
                        onSignupSuccess();
                    }else{
                        Toast.makeText(getActivity(),"User already exists",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Please connect network",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        _signupButton.setEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.
        register();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        ((MainActivity) getActivity()).changeFragment(new QuoteFragment().setUsername(_usernameText.getText().toString()), false);
        //   setResult(RESULT_OK, null);
        //    finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getContext(), "Sign up failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();


        if (username.isEmpty() ||username.length() < 6) {
            _usernameText.setError("must be at least 6 characters");
            valid = false;
        }else if(!username.matches("^[a-zA_Z0-9_.]{3,15}$")){
            _usernameText.setError("Letter and chacrater is [a-z] [1-9]");
            valid = false;
        }else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 10) {
            _passwordText.setError("between 6 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }


}
