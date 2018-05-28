package mindmap.nodes;

public enum NodeType {
    ELLIPSE("ellipse"){
        public Node createNode(String text){
            return new MMEllipse(text);
        }
    };

    NodeType(String type){
        this.type=type;
    }
    private String type;
    public abstract Node createNode(String text);

    public static Node create(String type,String text){
        for(NodeType t: NodeType.values()){
            if(t.type==type){
                return t.createNode(text);
            }
        }
        return null;
    }
}
