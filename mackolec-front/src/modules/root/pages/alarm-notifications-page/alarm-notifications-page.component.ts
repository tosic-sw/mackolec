import { Component, OnInit, QueryList, ViewChild } from '@angular/core';
import { AlarmNotification } from 'src/modules/shared/models/AlarmNotification';
import { NotificationService } from 'src/modules/shared/services/notification.service';
import { SnackBarService } from 'src/modules/shared/services/snack-bar.service';
import { PaginationComponent } from '../../components/pagination/pagination.component';

@Component({
  selector: 'app-alarm-notifications-page',
  templateUrl: './alarm-notifications-page.component.html',
  styleUrls: ['./alarm-notifications-page.component.scss']
})
export class AlarmNotificationsPageComponent implements OnInit {

  notifications: AlarmNotification[];
  pageSize: number;
  currentPage: number;
  totalSize: number;
  notificationTypes: string[];
  notificationTypeSelected: string;

  @ViewChild(PaginationComponent) pagination!: PaginationComponent;

  constructor(private alarmNotificationService: NotificationService, private snackBarService: SnackBarService) { 
    this.notifications = [];
    this.pageSize = 11;
    this.currentPage = 1;
    this.totalSize = 1;
    this.notificationTypes = ['Heart', 'Oxygen', 'Fluid']
    this.notificationTypeSelected = '';
  }

  ngOnInit(): void {
    this.changePage(this.currentPage);
  }

  changePage(newPage: any) {
    let newPageNumber = newPage as number;

    this.alarmNotificationService.findAll(this.notificationTypeSelected, newPageNumber - 1, this.pageSize).subscribe((response: any) => {
      this.notifications = response.body;
      this.totalSize = Number(response.headers.get("total-elements"));

      if(newPage === 1)
        this.pagination.reset();
    },
    (error) => {
      if(error.status === 500)
        this.snackBarService.openSnackBar("An unknown error ocured while loading notifications");
    });
  }

  notificationTypeChanged(option: any) {
    if(!option) option = '';
    
    let optionStr = option as string;
    optionStr = optionStr.toUpperCase();
    this.notificationTypeSelected = optionStr;

    this.changePage(1);
  }

}
