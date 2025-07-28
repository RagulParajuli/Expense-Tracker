# Expense Tracker Android Application

A modern Android application built with Kotlin to help users track and manage their expenses efficiently.

## Features

### 1. Home Dashboard
- Interactive pie chart visualization of expenses by category
- Bottom navigation for easy access to different sections
- Quick overview of spending patterns

### 2. Spending Tracking
- Time period filtering options:
  - Today
  - Last Month
  - Last 2 Months
  - Last 3 Months
- Detailed expense list with:
  - Date
  - Category
  - Description
  - Total Amount
  - Your Share
- Delete functionality for expense entries

### 3. Analysis Section
- Bar chart visualization for expense categories
- Detailed breakdown of spending by categories:
  - Food
  - Fuel
  - Grocery
  - And more
- Visual representation of spending patterns

## Technical Implementation

### Architecture
- Built using Kotlin
- Follows Android Activity lifecycle
- Implements RecyclerView for efficient list handling
- Uses MPAndroidChart library for data visualization

### Key Components

1. **Activities**
   - `MainActivity`: Entry point with Get Started button
   - `HomeActivity`: Dashboard with pie chart
   - `SpendingActivity`: Expense list and time period filtering
   - `AnalysisActivity`: Expense analysis with bar charts

2. **Adapters**
   - `ExpenseAdapter`: Handles expense list display with RecyclerView
   - Custom ViewHolder pattern implementation

3. **Data Models**
   - `ExpenseItem`: Data class for expense entries

4. **UI Features**
   - Edge-to-edge display support
   - Interactive charts and graphs
   - Responsive bottom navigation
   - Custom layouts for expense items

## Libraries Used
- AndroidX Core KTX
- MPAndroidChart for data visualization
- RecyclerView for list management
- Material Design components

## Future Enhancements
- Data persistence with Room Database
- User authentication
- Expense categories customization
- Export functionality for expense reports
- Budget setting and tracking

## Sample Screenshots or UI Mockups
<img width="355" height="619" alt="image" src="https://github.com/user-attachments/assets/b87ee40a-293a-4d43-87a0-e1da5ea58f72" />
<img width="355" height="619" alt="image" src="https://github.com/user-attachments/assets/dc56b30c-2ff0-4064-938b-c0779c97ea89" />
<img width="355" height="619" alt="image" src="https://github.com/user-attachments/assets/ad1a665f-a055-412c-8d44-1f5865d81971" />
<img width="355" height="619" alt="image" src="https://github.com/user-attachments/assets/cfbc5c78-d6b1-4e67-b0bc-f979635b7535" />
<img width="355" height="619" alt="image" src="https://github.com/user-attachments/assets/1a1717e0-df8d-4131-bf47-96717eefcb65" />
<img width="355" height="619" alt="image" src="https://github.com/user-attachments/assets/60a590cb-2800-45f0-aa81-e0f55539baab" />
