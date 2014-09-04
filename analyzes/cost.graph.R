# TODO: This script aims to build the graph to illustrate the location costs.
# 
# Author: Lucas Venezian Povoa
###############################################################################

setwd("/home/lucas/workspace/BPD4CD/analyzes/")

require("ggplot2")

costs <- read.csv("data/selection_costs.csv", sep = ",", header = F)

colnames(costs) <- 
		c("execution_cost", "monetary_cost", "privacy_cost", "total_cost", "location_selection", "cloud")

costs$location_selection <- strtoi(costs$location_selection, base = 2)

costs <- costs[costs$cloud == 1,]

costs$index <- 1:nrow(costs)

plot <- ggplot(costs, aes(x = 1, y = total_cost)) +
		geom_boxplot()

plot
cloud.pricing.model.rfit <- function(
		memory_in_gb,           # amount of RAM in GB
		ghz_by_core,            # frequency of each virtual core
		hard_disk_in_gb,        # amount of hard disk in GB
		number_of_virtual_cores # number of virtual cores
) {
	
	return(-2.4882e-01 + 1.3506e-02 * memory_in_gb + 7.2481e-02 * number_of_virtual_cores + 8.3593e-02 * ghz_by_core + 9.2282e-05 * hard_disk_in_gb)
}





