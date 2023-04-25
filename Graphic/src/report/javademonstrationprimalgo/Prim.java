package report.javademonstrationprimalgo;

import java.util.Calendar;
import java.util.Random;
public class Prim{
    public int nNodes = 50;
    public int nAretes = nNodes * nNodes;
    public Edge[] tree = new Edge[nNodes];
    public int[] papa = new int[nNodes];
    public Edge[] temp = new Edge[nNodes];

    public boolean[] tab=new boolean[nNodes];
    public Node[] node;
    Random r = new Random();
    Calendar date=Calendar.getInstance();


    public Prim(Edge[] depart, int nNode ,int nbarret){
        initNod(nNode);
        prim(depart,nNode,nbarret,papa,tree);
    }

    public void initNod(int nbnoeud){
        node = new Node[nbnoeud];
        for (int i=0;i<nbnoeud;i++){
            node[i]=new Node(i+1);
        }
    }

    public void init(int[] t){

        for (int i = 0; i < t.length; i++){
            t[i] = -1;
            tab[i]=false;
        }
    }

    public boolean chercheEnsemble(int k){

        boolean tmp=true;
        for(int i=0;i<k;i++){

            if(!tab[i]){
                tmp=false;
            }
        }
        return tmp;
    }

    public void triGraph(Edge[] t, int nbarret){

        int j;
        int k;
        Edge a;

        for (int i = 0; i < nbarret; i++){
            j = i;
            for (k = j; k < nbarret; k++){
                if (t[k].getWeight() < t[j].getWeight()){
                    j = k;
                }
            }
            a = t[j];
            t[j] = t[i];
            t[i] = a;
        }
        return;
    }

    public boolean cycle(int []pere, int a, int b){

        int i = a;
        int j = b;
        while (pere[i] > 0){

            i = pere[i];
        }
        while (pere[j] > 0){

            j = pere[j];
        }
        if (i != j){
            pere[i] = j;
        }
        return i != j;
    }
    public boolean prim(Edge[] g, int nNodes, int nbarret, int[]
            papa,Edge[] tree)
    {
        init(papa);

        int nb = 0;
        triGraph(g,nbarret);
        r.setSeed(date.getTimeInMillis());

        Node tmp = node[r.nextInt(nNodes)];

        tab[tmp.GetNode() -1]=true;

        while (!chercheEnsemble(nNodes)){
            boolean find =false;
            int ia = 0;
            int i=1;
            int cpt=0;

            while(i<=nNodes){
                for(int j=0;j<nbarret;j++){
                    if(i==g[j].getNode1() || i==g[j].getNode2()){

                        if( (tab[ ( g[j].getNode1() -1 ) ] && !tab[ (
                                g[j].getNode2() -1 ) ])||(!tab[ (
                                g[j].getNode1() -1 ) ] && tab[(
                                g[j].getNode2() -1 ) ]))
                        {

                            temp[cpt]=g[j];
                            cpt++;
                        }
                    }
                }
                i++;
            }
            if(cpt!=0){
                triGraph(temp,cpt);
                while(ia<cpt && !find){

                    if (cycle(papa, temp[ia].getNode1(),
                            temp[ia].getNode2()))
                    {
                        tree[nb] = temp[ia];
                        nb++;

                        tab[temp[ia].getNode1() -1]=true;
                        tab[temp[ia].getNode2() -1]=true;
                        find=true;
                    }
                    ia++;
                }
            }
        }
        return nb == nNodes - 1;
    }
}


