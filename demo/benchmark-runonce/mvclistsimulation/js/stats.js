var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "75000",
        "ok": "75000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "203",
        "ok": "203",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2207",
        "ok": "2207",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1102",
        "ok": "1102",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "550",
        "ok": "550",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1399",
        "ok": "1399",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1500",
        "ok": "1499",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1675",
        "ok": "1675",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1943",
        "ok": "1943",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 22958,
        "percentage": 31
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 6633,
        "percentage": 9
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 45409,
        "percentage": 61
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "688.073",
        "ok": "688.073",
        "ko": "-"
    }
},
contents: {
"req_mvclist-dde7f": {
        type: "REQUEST",
        name: "mvclist",
path: "mvclist",
pathFormatted: "req_mvclist-dde7f",
stats: {
    "name": "mvclist",
    "numberOfRequests": {
        "total": "75000",
        "ok": "75000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "203",
        "ok": "203",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2207",
        "ok": "2207",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1102",
        "ok": "1102",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "550",
        "ok": "550",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1399",
        "ok": "1399",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1500",
        "ok": "1499",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1675",
        "ok": "1675",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1943",
        "ok": "1943",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 22958,
        "percentage": 31
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 6633,
        "percentage": 9
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 45409,
        "percentage": 61
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "688.073",
        "ok": "688.073",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
