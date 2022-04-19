package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

public class TeamService {
    static int counter=1;
    final int max_number=5;
    Programmer team[]=new Programmer[max_number];
    int total=0;
    public Programmer[] getTeam(){
        Programmer team[]=new Programmer[total];
        for (int i=0;i<=team.length;i++){
            team[i]=this.team[i];
        }
        return team;
    }
    public Programmer[] addMember(Employee e) throws TeamException{
        if(total>max_number){
            throw new TeamException("members are full!");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("he isn't a programmer");
        }
        Programmer p = (Programmer)e;
        if (isExist(p)){
            throw new TeamException("he has already in this team");
        }
        switch (p.getStatus()){
            case BUSY:
                throw new TeamException("该员工已在本开发团队在");
            case VOCATION:
                throw new TeamException("he is on vacation");
        }
        int Pro_num=0;
        int Des_num=0;
        int Arc_num=0;
        for(int i=0;i<=total;i++){
            if (team[i] instanceof Programmer){
                Pro_num++;
            }
            if (team[i] instanceof Architect){
                Arc_num++;
            }
            if (team[i] instanceof Designer){
                Des_num++;
            }
        }
        if (p instanceof Architect && Arc_num>1){
            throw new TeamException("arc:1 is max!");
        }
        if (p instanceof Designer && Des_num>2){
            throw new TeamException("designer:2 is max!");
        }
        if (p instanceof Programmer && Pro_num>3){
            throw new TeamException("Programmer:3 is max!");
        }
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;
        return null;
    }

    private boolean isExist(Programmer p) {
        for(int i = 0;i < total;i++){
            if(team[i].getId() == p.getId()){
                return true;
            }
        }
        return false;
    }
    public Programmer[] removeMember(int memberid) throws TeamException {
        int i;
        for (i=0;i<total;i++){
            if (team[i].getMemberId()==memberid){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if(i == total){
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }
        for (int j=i;j<total-1;j++){
            team[j]=team[j+1];
        }
        team[total-1]=null;
        total--;
        return null;
    }
}
