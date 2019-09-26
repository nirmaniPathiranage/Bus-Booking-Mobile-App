package com.example.nano_science.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChatListAdapter extends ArrayAdapter<ChatList> {

    Context ctx;
    int layoutRes;
    List<ChatList> chatList;
    SQLiteDatabase mDatabase;

    public ChatListAdapter(Context ctx, int layoutRes, List<ChatList> chatList, SQLiteDatabase mDatabase) {
        super(ctx, layoutRes, chatList);

        this.ctx = ctx;
        this.layoutRes = layoutRes;
        this.chatList = chatList;
        this.mDatabase = mDatabase;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(layoutRes, null);

        TextView textViewFullName = view.findViewById(R.id.textViewFullName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewContact = view.findViewById(R.id.textViewContact);
        TextView textViewLanguage = view.findViewById(R.id.textViewLanguage);
        TextView textViewMessage = view.findViewById(R.id.textViewMessage);

        final ChatList chatListt = chatList.get(position);

        textViewFullName.setText(chatListt.getFullname());
        textViewEmail.setText(chatListt.getEmail());
        textViewContact.setText(chatListt.getContact());
        textViewLanguage.setText(chatListt.getLanguage());
        textViewMessage.setText(chatListt.getMessage());


        Button buttonDelete = view.findViewById(R.id.buttonDeleteChat);
        Button buttonEdit = view.findViewById(R.id.buttonEditChat);

        //adding a clicklistner to button

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateChat(chatListt);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteChat(chatListt);

            }
        });

        return view;
    }

    private void updateChat(final ChatList cl) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.dialog_update_chat, null);
        builder.setView(view);

        final EditText editTextFullName = view.findViewById(R.id.editText_fullname1);
        final EditText editTextEmail = view.findViewById(R.id.editText_emailaddress1);
        final EditText editTextContact = view.findViewById(R.id.editText_contact1);
        final Spinner spinner = view.findViewById(R.id.spinnerLanguagetype1);
        final EditText editTextMessage = view.findViewById(R.id.editText_message1);

        editTextFullName.setText(cl.getFullname());
        editTextEmail.setText(cl.getEmail());
        editTextContact.setText(cl.getContact());
        editTextMessage.setText(cl.getMessage());

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        view.findViewById(R.id.buttonUpdateChat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = editTextFullName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextContact.getText().toString().trim();
                String language = spinner.getSelectedItem().toString();
                String message = editTextMessage.getText().toString().trim();

                //validate data

                if (fullname.isEmpty()) {
                    editTextFullName.setError("Full Name Field can't be empty");
                    editTextFullName.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    editTextEmail.setError("Email Address Field can't be empty");
                    editTextEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please enter a valid Email address");
                    editTextEmail.requestFocus();
                    return;
                }

                if (phone.isEmpty()) {
                    editTextContact.setError("Contact No. Field can't be empty");
                    editTextContact.requestFocus();
                    return;
                }

                if (message.isEmpty()) {
                    editTextMessage.setError("Message Field can't be empty");
                    editTextMessage.requestFocus();
                    return;
                }


                String sql = "UPDATE chat \n" + "SET fullname = ?, \n" + "email = ?, \n" + "phone = ?, \n" + "language = ?, \n" + "message = ? \n" + "WHERE messageid = ?; \n";

                mDatabase.execSQL(sql, new String[]{fullname, email, phone, language, message, String.valueOf(cl.getChatid())});

                Toast.makeText(ctx, "Messsage Updated Successfully", Toast.LENGTH_SHORT).show();

                LoadMenuFromDatabaseAgain();

                alertDialog.dismiss();

            }
        });
    }

    private void deleteChat(final ChatList cl) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setTitle("Are you sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String sql = "DELETE FROM chat WHERE messageid = ?";
                mDatabase.execSQL(sql, new Integer[]{cl.getChatid()});
                LoadMenuFromDatabaseAgain();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void LoadMenuFromDatabaseAgain() {
        String sql = "SELECT * FROM chat";

        Cursor cursor = mDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            chatList.clear();
            do {

                chatList.add(new ChatList(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));


            } while (cursor.moveToNext());

            cursor.close();
            notifyDataSetChanged();
        }
    }
}