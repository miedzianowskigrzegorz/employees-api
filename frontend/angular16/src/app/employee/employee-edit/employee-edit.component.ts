import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { EmployeeService } from '../service/employee.service';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {

  employeeId!: number;
  employee!: Employee;

  constructor(private route: ActivatedRoute,
  private employeeService: EmployeeService,
  private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.employeeId = +params['id'];
      this.loadEmployee();
    });
  }

  loadEmployee() {
    this.employeeService.getEmployeeById(this.employeeId).subscribe(
      (data: Employee) => {
        this.employee = data;
      },
      (error: any) => {
        console.log('Error getting data:', error);
      }
    );
  }

  editEmployee() {
    console.log(this.employee);
    this.employeeService.updateEmployee(this.employee).subscribe(result => this.gotoEmployeeList());
  }



  gotoEmployeeList() {
      this.router.navigate(['/employees']);
  }
}
