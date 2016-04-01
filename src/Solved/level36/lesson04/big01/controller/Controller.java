package Solved.level36.lesson04.big01.controller;

import Solved.level36.lesson04.big01.model.Model;
import Solved.level36.lesson04.big01.view.EditUserView;
import Solved.level36.lesson04.big01.view.UsersView;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class Controller
{
    private UsersView usersView;
    private Model model;
    private EditUserView editUserView;

    public void setEditUserView(EditUserView editUserView)
    {
        this.editUserView = editUserView;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void onShowAllUsers() {
        model.loadUsers();
        usersView.refresh(model.getModelData());
    }

    public void setUsersView(UsersView usersView)
    {
        this.usersView = usersView;
    }

    public void onShowAllDeletedUsers() {
        model.loadDeletedUsers();
        usersView.refresh(model.getModelData());
    }

    public void onOpenUserEditForm(long userId) {
        model.loadUserById(userId);
        editUserView.refresh(model.getModelData());
    }

    public void onUserDelete(long id){
        model.deleteUserById(id);
        usersView.refresh(model.getModelData());
    }

    public void onUserChange(String name, long id, int level){
        model.changeUserData(name, id, level);
        usersView.refresh(model.getModelData());
    }
}
