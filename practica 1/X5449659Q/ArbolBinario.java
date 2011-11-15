package practica1;


@SuppressWarnings("unchecked")
class ArbolBinario implements Comparable{
	
	private String    content;
	private double  value ;
	private ArbolBinario    left;
	private ArbolBinario    right;

 	public ArbolBinario(String content,double value){
        this.content  = content;
        this.value = value ; 
        this.left = null ; 
        this.right = null ; 
 	}

    public ArbolBinario(ArbolBinario left, ArbolBinario right)
    {
            
            this.content  =    left.content+right.content;
            this.value    = left.value + right.value;
            this.left         = left;
            this.right    = right;
    }
   
 	public ArbolBinario getIzq() { 
 		return left;
 	}
 	public ArbolBinario getDer() { 
 		return right ; 
 	}
 	
 	private void printNode(String cad,CodeBook c)
 	{
        if ((left==null) && (right==null))
        	c.add(content.charAt(0), cad);
                //System.out.println(content + " " + cad);

        if (left != null)
                left.printNode(cad + '1',c);
        if (right != null)
                right.printNode(cad + '0',c);
 	}
    
 	public void setIzq(ArbolBinario a) { 
 		this.left = a ;
 		
 	}
 	public void setDer(ArbolBinario a) { 
 		this.right = a ;
 	}
 	public static void printTree(ArbolBinario tree,CodeBook c)
 	{
        tree.printNode("",c);
 	}

	

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		//int res = -1 ;
		ArbolBinario aux = (ArbolBinario) arg0 ;
		if(this.value == aux.value) return this.content.compareTo(aux.content);
		else return Double.compare(this.value, aux.value);
		
	}
   
	
}