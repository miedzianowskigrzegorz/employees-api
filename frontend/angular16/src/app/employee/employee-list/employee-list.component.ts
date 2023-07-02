import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../service/employee.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  templateUrl: 'employee-list.component.html',
  styleUrls: ['employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

    employees!: Employee[];
    showModal: boolean = false;
    employeeId!: number;
    searchForm: FormGroup;

    constructor(private employeeService: EmployeeService,private formBuilder: FormBuilder) {
     this.searchForm = this.formBuilder.group({
           searchQuery: ['']
         });
     }

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

    searchEmployee() {
        const searchQuery = this.searchForm.get('searchQuery')?.value;
        console.log('Searching:', searchQuery);
        this.employeeService.findEmployeeBySearchRequest(searchQuery).subscribe(employees => {
                this.employees = employees;
              });

        this.searchForm.reset();
      }
}
