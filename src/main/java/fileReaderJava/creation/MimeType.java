package fileReaderJava.creation;

import java.util.HashMap;
import java.util.Map;

public enum MimeType {

        CSV("text/csv"),
        EXCEL("application/vnd.ms-excel");

        private String kind;


         // Map initialised before constructor to allow storing of Mime type key against
         //String value (kind)

        private static class Holder {
            static Map<String, MimeType> MAP = new HashMap<>();
        }

        MimeType (String kind) {
            this.kind = kind;
            Holder.MAP.put(kind,this);
        }

        public String type(){
            return kind;
        }

        /**
         * For a given mimetype find the matching value in the enum
         * @param kind
         * @return enum value of mimetype
         */
        public static MimeType find(String kind) {
            MimeType mimeType = Holder.MAP.get(kind);
            if(mimeType == null) {
                throw new IllegalStateException(String.format("Unsupported type %s.", kind));
            }
            return mimeType;
        }



    }





