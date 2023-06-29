import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../service/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: 'employee-list.component.html',
  styleUrls: ['employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

    employees!: Employee[];
    showModal: boolean = false;
    employeeId!: number;

    constructor(private employeeService: EmployeeService) { }

    ngOnInit() {
      this.loadEmployees();
    }

    loadEmployees() {
      this.employeeService.findAll().subscribe(employees => {
        this.employees = employees;
      });
    }

    closeModal() {
      this.showModal = false;
    }

    confirmDelete(employeeId: number) {
      this.employeeId = employeeId;
      this.showModal = true;
    }

    deleteEmployee(employeeId: number) {
      this.employeeService.deleteEmployee(employeeId).subscribe(() => {
        this.closeModal();
        this.loadEmployees();
      });
    }
  }
