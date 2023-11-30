package controller;

import dtos.TaskDTO;
import sevices.TaskService;

import java.util.Scanner;

public class TaskController {
    private TaskService taskService = new TaskService();

    public void start() {
        showMenu();
        boolean bool = true;
        int n;
        while (bool){
            n = getAction();
            switch (n) {
                case 1: createTask();
                    break;
                case 2:
                    System.out.println("Active List:");
                    taskService.activeTaskList();
                    break;
                case 3:
                    System.out.println("Finished Task list");
                    getFinishedTasks();
                    break;
                case 4:
                    System.out.println("Update by id");
                    update();
                    break;
                case 5:
                    System.out.println("Delete by id");
                    delete();
                    break;
                case 6:
                    System.out.println("Mark as Done");
                    markAsDone();
                case 7:
                    System.out.print("Searching: ");
                    search();
                    break;
                default:
                    System.out.println("1 va 7 oraliqda son kiriting");
                    bool = false;
                    break;
            }
        }

    }

    private void createTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Content:");
        String content = scanner.nextLine();

        TaskDTO taskDTO = new TaskDTO();
         taskDTO.setTitle(title);
         taskDTO.setContent(content);
         taskService.createTask(taskDTO);
    }
    private void search(){
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Search...");
        String title = scanner.nextLine();
        taskService.search(title);
    }

    private void showMenu() {
        System.out.println("*****Task Manager*****");
        System.out.println(" 1.Create task: ");
        System.out.println(" 2.Active Task List: ");
        System.out.println(" 3.Finished Task List: ");
        System.out.println(" 4.Update (by id): ");
        System.out.println(" 5.Delete by id: ");
        System.out.println(" 6.Mark as Done: ");
        System.out.println(" 7.Searching: ");
    }
    private int getAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose menu:");
        return scanner.nextInt();
    }

    private void markAsDone() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id :");
        int id = scanner.nextInt();
        var  isMarked =  taskService.mark(id);
        if(isMarked){
            System.out.println("Status changed successfully");
        }
    }
    private void update() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("enter Id : ");
        int id = scanner.nextInt();

        System.out.println("enter title : ");
        String title  = scanner2.nextLine();

        System.out.println("enter content : ");
        String content = scanner2.nextLine();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(title);
        taskDTO.setContent(content);

        var isUpdated =  taskService.update(taskDTO,id);
        if(isUpdated){
            System.out.println("Updated successfully");
        }
    }
    private void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id : ");
        int id = scanner.nextInt();
        var isDeleted = taskService.delete(id);
        if(isDeleted){
            System.out.println("deleted successfully");
        }
    }
    private void getFinishedTasks() {
//        var allActiveTasks =   taskService.getALlFinishedTasks();
//        System.out.println("------------------------------------------------------------------");
//        System.out.printf("%-5s %-20s %-30s %-15s %-30s %-25s\n",
//                "ID", "Title", "Content", "Status", "Created At", "Finished At");
//
//        // Print table data
//        for (TaskDTO task : allActiveTasks) {
//            System.out.printf("%-5s %-20s %-30s %-15s %-30s %-25s\n",
//                    task.getId(), task.getTitle(), task.getContent(),
//                    task.getStatus(), task.getCreatedAt(), task.getFinishedAt());
//        }
//
//        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}
