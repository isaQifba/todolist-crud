package edu.ifba.saj.todo_list.constants;

public enum StatusENUM {
    
    PARA_FAZER("para_fazer"),
    FAZENDO("fazendo"),
    FEITO("feito");

    private final String status;

    StatusENUM(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static StatusENUM fromString(String s){
        for(StatusENUM status : StatusENUM.values()){
            if(status.getStatus().equals(s.toLowerCase())){
                return status;
            }
        }
        
        throw new IllegalArgumentException("Status de nome {" + s + "} é inválido.");
    }

}
