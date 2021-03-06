package Lab4.IO.Contacts;

import Lab4.IO.Contacts.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook {
    private int counter;
    private Contact[] contacts= new Contact[10];
    Scanner s=null;

    public PhoneBook(String inputFile){
        try{
            Scanner s=new Scanner(new BufferedReader(new FileReader(inputFile)));
            String name;
            String number;
            while(s.hasNext()){
               name=s.next();
               if(s.hasNext()){
                   number=s.next();
                   storeContact(new Contact(name,number));
               }
            }
        }catch(IOException ex){
            System.out.println(ex);
        }finally{
            if(s!=null)
                s.close();
        }
    }

    private void storeContact(Contact contact){
        if(counter<contacts.length){
            contacts[counter]=contact;
            counter++;
        }
        else{
            Contact[] newContacts= new Contact[contacts.length*2];
            int i=0;
            for(Contact currentContact:contacts){
                newContacts[i++]=currentContact;
            }
            newContacts[i++]=contact;
            contacts=newContacts;
            counter=i;
        }
    }

    public String toString(){
        String toReturn="";
        for(int i=0;i<counter;i++){
            toReturn+= contacts[i].toString()+"\n";
        }
        return toReturn;
    }

    public Contact findContact(String name){
        for(int i=0;i<counter;i++)
        {
            if(contacts[i].getName().equals(name)){
                return contacts[i];
            }
        }
        return null;
    }
}
