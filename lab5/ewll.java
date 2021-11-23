package lab5;

public class ewll {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
//        while (in.hasNext()) {
            int n = in.nextInt(); //num of deque
            int q = in.nextInt(); //num of operation
            DoubleLink[] cur = new DoubleLink[n + 1];
            DoubleLink[] heads=new DoubleLink[n+1];
            DoubleLink[] tails=new DoubleLink[n+1];
            for (int i = 1; i < n + 1; i++) {
                heads[i]=new DoubleLink(-1);
                tails[i]=new DoubleLink(-1);
                heads[i].next=tails[i];
                tails[i].pre=heads[i];
            }

            for(int i=0;i<q;i++){
                int type=in.nextInt();
                switch (type){
                    case 1:
                        int u=in.nextInt();
                        int w=in.nextInt();
                        int val=in.nextInt();
                        if(w==0){
                            DoubleLink temp=new DoubleLink(val);
                            temp.next=heads[u].next;
                            temp.pre=heads[u];
                            heads[u].next=temp;
                            temp.next.pre=temp;
                        }
                        else{
                            DoubleLink temp=new DoubleLink(val);
                            temp.pre=tails[u].pre;
                            temp.next=tails[u];
                            tails[u].pre=temp;
                            temp.pre.next=temp;
                            //dequeLs[u].insertrear(val);
                        }
                        break;
                    case 2:
                        int u2=in.nextInt();
                        int w2=in.nextInt();

                        if(w2==0){ //front
                            if(heads[u2].next.next==null){
                                out.println(-1);
                            }
                            else {
                                out.println(heads[u2].next.val);
                                heads[u2].next=heads[u2].next.next;
                                heads[u2].next.pre=heads[u2];
                            }
                            //out.println(dequeLs[u2].popFront());
                        }
                        else{
                            if(tails[u2].pre.pre==null){
                                out.println(-1);
                            }
                            else {
                                out.println(tails[u2].pre.val);
                                tails[u2].pre=tails[u2].pre.pre;
                                tails[u2].pre.next=tails[u2];
                            }
                            //out.println(dequeLs[u2].popRear());
                        }
                        break;
                    case 3:
                        int u3=in.nextInt();
                        int v3=in.nextInt();
                        int w3=in.nextInt();
                        if(w3==0){ //connect v's front to rear of u
                            if(heads[v3].next==null){

                            }
                            else {
                                heads[v3].next.pre=tails[u3].pre;

                                tails[v3].pre.next=tails[u3];
                                tails[u3].pre.next=heads[v3].next;
                                tails[u3].pre=tails[v3].pre;
                                heads[v3].next=tails[v3];
                                tails[v3].pre=heads[v3];
//                                tails[v3].pre.next=tails[u3];
//                                tails[u3].pre=
                            }
                        }
                        if(w3==1){
                            if(tails[v3].pre==null){

                            }
                            else { //v3 is 2, u3 is 1
                                tails[u3].pre.next=tails[v3].pre;
                                tails[v3].pre.next=tails[u3].pre;
                                heads[v3].next.pre=tails[u3];


                                tails[u3].pre=heads[v3].next;
                                heads[v3].next=tails[v3];
                                tails[v3].pre=heads[v3];
                            }
                        }

                        break;
                }
//            }



        }
            out.close();
    }
}

class DoubleLink{
    int val;
    DoubleLink next;
    DoubleLink pre;

    public DoubleLink(int val){
        this.val=val;
    }
}

