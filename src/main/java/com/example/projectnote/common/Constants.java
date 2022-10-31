package com.example.projectnote.common;

public class Constants {

    public enum ExceptionClass{

        USER("User"), NOTE("Note"), EVENT("Event");
        private String exceptionClasses;

        ExceptionClass(String exceptionClass) {
            this.exceptionClasses = exceptionClass;
        }

        public String getExceptionClass(){
            return exceptionClasses;
        }

        @Override
        public String toString(){
            return getExceptionClass() + " Exception. ";
        }
    }
}
